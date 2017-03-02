package com.jay.anno;

import java.lang.annotation.*;

/**
 * 在Spring MVC的请求参数上，标记用于自动解密参数
 * 作用域： 请求处理方法的入参
 * Created by hetiewei on 2017/3/1.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JayJsonRequestDecrypt {
}
