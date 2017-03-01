package com.jay.config;//package com.jay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring Boot MVC配置
 *
 * Created by hetiewei on 2017/2/23.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    /*
	 * 配置简易的View， 默认页面
	 *  /, /index都指向 index页面
	 */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/login").setViewName("/user/login");
    }
}
