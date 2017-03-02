package com.jay.anno;

import java.lang.annotation.*;

/**
 * 在Spring MVC的请求参数上，标记用于Base64自动转码的注解
 * 作用域： 请求处理方法
 * Created by hetiewei on 2017/3/1.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JayJsonResponseBase64 {
}
