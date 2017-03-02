package com.jay.config.mvc;

import com.alibaba.fastjson.JSONObject;
import com.jay.anno.JayJsonRequestDecrypt;
import com.jay.util.encrypt.EncryptAndDecryptUtils;
import com.jay.vo.base.BaseRequestVo;
import com.jay.vo.base.BaseResponseVo;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * 入参进行自动解密，
 * 通过识别 @JayRequestDecrypt注解，自动对入参进行解密
 * 1.入参加密规则：         des           指定私钥 secretKey (eg:12345678)
 *     1.请求体body使用des加密
 *     2.请求体格式为 BaseRequestVo(timeStamp, sign, data)
 *
 * 2.签名校验规则：          md5
 *     1.timeStamp取请求当前时间戳
 *     2.sign = md5(timeStamp + secretKey + data)
 * 3.时间戳说明：
 *     超过30秒的报文直接丢弃
 *
 * Created by hetiewei on 2017/3/1.
 */
public class JayJsonRequestDecryptResolver implements HandlerMethodArgumentResolver {

    private static Logger logger = LoggerFactory.getLogger(JayJsonRequestDecryptResolver.class);

    private Charset defaultCharset = Charset.forName("UTF-8");

    //私钥 长度为8的倍数
    private String secretKey = "12345678";


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JayJsonRequestDecrypt.class);
    }

    /**
     * 对方法进行解密处理
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 编码设置
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpInputMessage message = new ServletServerHttpRequest(request);
        Charset charset = this.getContentCharset(message.getHeaders().getContentType());
        String body = FileCopyUtils.copyToString(new InputStreamReader(request.getInputStream(), charset));

        // 如果是GET请求，body应该是空，重新获取
        if (StringUtils.isEmpty(body)) {
            body = request.getQueryString();
        }

        try {
            String content = new String(Base64.decodeBase64(body), "UTF-8");
            return JSONObject.parseObject(content, BaseResponseVo.class);
        } catch (Exception ex) {


            try {
                logger.info("【请求body解密前】:{}",body);
                String debody = EncryptAndDecryptUtils.desDecrypt(body, secretKey);
                logger.info("【请求body解密后】:{}",debody);
                BaseRequestVo requestVo = JSONObject.parseObject(debody, BaseRequestVo.class);
                //1.获取请求数据
                String data = (String) requestVo.getData();
                logger.info("【请求data】:{}",data);

                //2.校验时间戳(请求超过指定时间eg:30秒，则丢弃该请求)
                Long timestamp = requestVo.getTimeStamp();
                Long timeDiff = System.currentTimeMillis() - timestamp;
                if (timeDiff >30){
                    return null;
                }

                //3.校验请求签名，根据规则对字符串MD5加密，并且根据加密后的值与sign进行比对,防止恶意请求，如果成功则转发请求，失败则做异常处理
                String sign = requestVo.getSign();
                String digestStr = EncryptAndDecryptUtils.md5Encrypt(timestamp+secretKey+data);

                logger.info("【加密后sign】:{}",digestStr);
                if (digestStr != null && digestStr.equals(sign)) {
                    //4.签名校验通过后，返回方法可以处理的参数
                    logger.info("the message body before decrypt are {}", body.toString());
                    logger.info("the message body decrypted are {}", debody);// 获取解密的消息体
                    return requestVo;
                }
            } catch (Exception e) {
                logger.error("Decrypt msg failed caused by" + e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
    }

    // 获取charset
    public Charset getContentCharset(MediaType mediaType) {

        if (mediaType != null && mediaType.getCharSet() != null) {
            return mediaType.getCharSet();
        } else {
            return this.defaultCharset;
        }

    }
}
