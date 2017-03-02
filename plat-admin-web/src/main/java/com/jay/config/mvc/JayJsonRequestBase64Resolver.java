package com.jay.config.mvc;

import com.alibaba.fastjson.JSONObject;
import com.jay.anno.JayJsonRequestBase64;
import com.jay.util.encrypt.EncryptAndDecryptUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 入参进行Base64自动解码，
 * 通过识别 @JayRequestBase64注解，自动对入参进行Base64解码
 * Created by hetiewei on 2017/3/1.
 */
public class JayJsonRequestBase64Resolver implements HandlerMethodArgumentResolver {

    private Charset defaultCharset = Charset.forName("UTF-8");

    /**
     * 标记处理的类型，使用 @TspRequestBase64注解的参数
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(JayJsonRequestBase64.class);
    }

    /**
     * 对符合条件的参数进行处理
     * @param parameter
     * @param modelAndViewContainer
     * @param webRequest
     * @param webDataBinderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpInputMessage message = new ServletServerHttpRequest(request);
        Charset charset = this.getContentCharset(message.getHeaders().getContentType());
        String body = FileCopyUtils.copyToString(new InputStreamReader(request.getInputStream(), charset));

        // 如果是GET请求，body应该是空，重新获取，参数在url上
        if (StringUtils.isEmpty(body)) {
            body = request.getQueryString();
        }

        byte[] content = null;

        if (body != null && body.length() > 0) {
            if (Base64.isBase64(body)) {
                content = Base64.decodeBase64(body);

            } else {
                content = body.getBytes(charset);
            }
        }

        System.out.println(new String(content, charset));

        return (content == null) ? null : JSONObject.parseObject(content, parameter.getParameterType());
    }


    /**
     * 获取charset
     * @param mediaType
     * @return
     */
    public Charset getContentCharset(MediaType mediaType) {

        if (mediaType != null && mediaType.getCharSet() != null) {
            return mediaType.getCharSet();
        } else {
            return this.defaultCharset;
        }

    }

    public Map<String, Object> getUrlParams(String param) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        if (StringUtils.isEmpty(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }

    public static void main(String args[]){
        System.out.println();
        System.out.println(EncryptAndDecryptUtils.base64Decrypt("bmFtZT10ZXN0"));
        System.out.println(Base64.isBase64("name=test"));
        System.out.println(org.springframework.security.crypto.codec.Base64.isBase64("name=test".getBytes()));

    }

}
