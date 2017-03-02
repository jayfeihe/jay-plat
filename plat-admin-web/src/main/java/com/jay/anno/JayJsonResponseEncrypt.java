package com.jay.anno;

import java.lang.annotation.*;

/**
 * 在Spring MVC的请求方法上，标记用于自动加密出参
 * 作用域：请求方法
 * Created by hetiewei on 2017/3/1.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JayJsonResponseEncrypt {
}
