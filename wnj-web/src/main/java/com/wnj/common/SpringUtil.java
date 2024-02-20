package com.wnj.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

@Component
public class SpringUtil implements ApplicationContextAware {

    private static AbstractApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.context = (AbstractApplicationContext) applicationContext;
    }

    public static Object getBean(String name){
        if(!isActive()){
            return null;
        }
        return context.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz){
        if(!isActive()){
            return null;
        }
        return context.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz){
        if(!isActive()){
            return null;
        }
        return context.getBean(name, clazz);
    }

    public static <T> Map<String,T> getBeansOfType(Class<T> clazz){
        if(!isActive()){
            return null;
        }
        return context.getBeansOfType(clazz);
    }

    public static Map<String,Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType){
        if(!isActive()){
            return null;
        }
        return context.getBeansWithAnnotation(annotationType);
    }


    private static boolean isActive() {
        return context != null && context.isActive();
    }
}
