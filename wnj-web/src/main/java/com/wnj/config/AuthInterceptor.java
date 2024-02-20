package com.wnj.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.wnj.common.LocalCache;
import com.wnj.common.Result;
import com.wnj.common.constant.GlobalConstant;
import com.wnj.domain.UserDO;
import com.wnj.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        // 从 http 请求头中取出 token
        String token = request.getHeader(GlobalConstant.HEADER_TOKEN);
        if(StringUtil.isBlank(token)){
            token = request.getParameter(GlobalConstant.TOKEN);
        }

        if(StringUtil.isBlank(token)){
            return responseData(response);
        }
        // 获取 token 中的 user id
        UserDO userDO = LocalCache.getObject(token);
        if(userDO == null) {
            return responseData(response);
        }

        //将验证通过后的用户信息放到请求中,继续往下执行
        request.setAttribute(GlobalConstant.USER_SESSION_KEY, userDO);
        return true;
    }
    public boolean responseData(HttpServletResponse response) throws IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String result = JSON.toJSONString(Result.buildFail4NoLogin("暂未登录"));
        PrintWriter writer = response.getWriter();
        writer.println(result);
        return false;
    }
}