package com.wnj.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;
import com.wnj.common.SpringUtil;
import com.wnj.util.StringUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * mybatis 自定义拦截器
 * 三步骤：
 *  1 实现 {@link Interceptor} 接口
 *  2 添加拦截注解 {@link Intercepts}
 *  3 配置文件中添加拦截器
 *
 * 1 实现 {@link Interceptor} 接口
 *      具体作用可以看下面代码每个方法的注释
 * 2 添加拦截注解 {@link Intercepts}
 *      mybatis 拦截器默认可拦截的类型只有四种，即四种接口类型 Executor、StatementHandler、ParameterHandler 和 ResultSetHandler
 *      对于我们的自定义拦截器必须使用 mybatis 提供的注解来指明我们要拦截的是四类中的哪一个类接口
 *      具体规则如下：
 *          a：Intercepts 标识我的类是一个拦截器
 *          b：Signature 则是指明我们的拦截器需要拦截哪一个接口的哪一个方法
 *              type    对应四类接口中的某一个，比如是 Executor
 *              method  对应接口中的哪类方法，比如 Executor 的 update 方法
 *              args    对应接口中的哪一个方法，比如 Executor 中 query 因为重载原因，方法有多个，args 就是指明参数类型，从而确定是哪一个方法
 * 3 配置文件中添加拦截器
 *      拦截器其实就是一个 plugin，在 mybatis 核心配置文件中我们需要配置我们的 plugin ：
 *          <plugin interceptor="liu.york.mybatis.study.plugin.MyInterceptor">
 *              <property name="username" value="LiuYork"/>
 *              <property name="password" value="123456"/>
 *          </plugin>
 *
 * 拦截器顺序
 * 1 不同拦截器顺序：
 *      Executor -> ParameterHandler -> StatementHandler -> ResultSetHandler
 *
 * 2 对于同一个类型的拦截器的不同对象拦截顺序：
 *      在 mybatis 核心配置文件根据配置的位置，拦截顺序是 从上往下
 */
@Component
@Intercepts({
//    @Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
    @Signature(method = "update", type = Executor.class, args = {MappedStatement.class, Object.class})
})
public class BizIdPlugin implements Interceptor {

    private static final int STATEMENT_IDX = 0;
    private static final int METHOD_PARAM_IDX = 1;

    private static final Map<String, BizId> CACHE = new HashMap<>();



    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement)args[STATEMENT_IDX];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if(sqlCommandType != SqlCommandType.INSERT){
            return invocation.proceed();
        }

        BizId bizId = null;
        String path = mappedStatement.getId();
        if(CACHE.containsKey(path)){
            bizId = CACHE.get(path);
        }else{
            int lastPoint = path.lastIndexOf('.');
            String clazzName = path.substring(0, lastPoint);
            String methodName = path.substring(lastPoint + 1);
            Class<?> clazz = Class.forName(clazzName);
            //ReflectUtil
            Method method = findMethod(clazz, methodName);
            if(method != null){
                bizId = method.getAnnotation(BizId.class);
                CACHE.put(path, bizId);
            }
        }

        if(bizId != null){
            Object params = args[METHOD_PARAM_IDX];
            if(params != null){
                setId(params, bizId);
            }
        }
        return invocation.proceed();
    }

    private void setId(Object params, BizId bizId) {
        if(params instanceof Map){
            Map map = (Map)params;
            if(StringUtil.isNotBlank(bizId.batch())){
                List list = (List)map.get(bizId.batch());
                setId(list, bizId);
            }else{
                IdGenerator idGenerator = SpringUtil.getBean(IdGenerator.class);
                map.put(bizId.idName(),idGenerator.id32(bizId.table()));
            }
        }else if(params instanceof List){
            List list = (List)params;
            for (Object param : list) {
                setId(param, bizId);
            }
        }else{
            Method method = findMethod(params.getClass(), "setUserId");
            IdGenerator idGenerator = SpringUtil.getBean(IdGenerator.class);
            try{
                method.invoke(params, idGenerator.id32(bizId.table()));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }


    @Override
    public void setProperties(Properties properties) {
        String username = properties.getProperty("username");
    }

    private Method findMethod(Class clazz, String methodName){
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if(method.getName().equals(methodName)){
                return method;
            }
        }
        return null;
    }
}
