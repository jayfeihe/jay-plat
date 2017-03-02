package com.jay.config.mvc;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.jay.anno.JayJsonResponseBase64;
import com.jay.util.encrypt.EncryptAndDecryptUtils;
import com.jay.util.tools.base.JsonUtil;
import com.jay.vo.base.BaseRequestVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

/**
 * 对请求出参进行Base64加密处理
 * Created by hetiewei on 2017/3/1.
 */
public class JayJsonResponseBase64Resolver implements HandlerMethodReturnValueHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private HttpMessageConverter messageConverter = new FastJsonHttpMessageConverter();

    /**
     * 仅支持异步返回数据，不支持返回页面
     *
     * 1.使用了 @JayResponseBase64注解的方法
     * 2.对请求返回的出参，统一父类都是 BaseRequestVo
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        Class<?> returnType =  methodParameter.getParameterType();
        if (methodParameter.hasMethodAnnotation(JayJsonResponseBase64.class) || BaseRequestVo.class.equals(returnType)){
            return true;
        }
        return false;
    }

    /**
     * 对出参数据进行Base64编码
     * @param returnValue
     * @param methodParameter
     * @param mavContainer
     * @param webRequest
     * @throws Exception
     */
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        logger.info("对请求出参自动Base64转码，转码前数据=【{}】", JsonUtil.toString(returnValue));
        ServletServerHttpResponse outputMessage = createOutputMessage(webRequest);
        if (returnValue != null) {
            messageConverter.write(EncryptAndDecryptUtils.base64Encrypt(JsonUtil.toString(returnValue)), new MediaType(MediaType.APPLICATION_JSON, Collections.singletonMap("charset", "UTF-8")),
                    outputMessage);
        }
    }

    protected ServletServerHttpResponse createOutputMessage(NativeWebRequest webRequest) {
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        return new ServletServerHttpResponse(response);
    }
}
