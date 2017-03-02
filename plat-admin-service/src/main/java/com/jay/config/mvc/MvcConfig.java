package com.jay.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by hetiewei on 2017/3/2.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{

    /**
     * 添加分页信息
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new PaginationArgumentResolver());
    }
}
