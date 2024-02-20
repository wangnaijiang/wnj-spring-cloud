package com.wnj.mybatis;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BizId {
    String table() default "user";
    String idName();
    String batch() default "";
    int length() default 32;
//    String sequence();
//    String generator() default "defaultIdGenerator";
}
