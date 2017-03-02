package com.jay.config;//package com.jay.config;

import com.jay.config.mvc.JayJsonRequestBase64Resolver;
import com.jay.config.mvc.JayJsonResponseBase64Resolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

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

    /**
     * 配置mvc请求入参解析器
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        //添加入参Base64解码
        argumentResolvers.add(new JayJsonRequestBase64Resolver());
        //添加入参解密
//        argumentResolvers.add(new JayJsonRequestDecryptResolver());
    }

    /**
     * 配置mvc参数返回解析器
     * @param returnValueHandlers
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        super.addReturnValueHandlers(returnValueHandlers);
        //添加出参Base64编码
        returnValueHandlers.add(new JayJsonResponseBase64Resolver());
        //添加出参加密
//        returnValueHandlers.add(new JayJsonResponseEncryptResolver());
    }
}
