package com.jay.service.base.impl;

import com.jay.util.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 调用微服务的公共服务类
 * Created by hetiewei on 2017/3/2.
 */
@Service
public class BaseCloudService {
    /**
     * Rest方式，负载均衡调用微服务
     */
    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    /**
     * 需要调用的微服务
     */
    @Value("${cloud.plat.admin.web.service}")
    protected String cloudService;

    /**
     * get方式调用微服务，传递调用的Rest风格url和参数
     * 返回 ResponseVo 对象
     * @param url  请求微服务的Restful  url
     * @param params  Get请求参数
     * @return
     */
    public ResponseVo getForResponseVo(String url, Map<String, ?> params){
        ResponseVo responseVo = restTemplate.getForObject(url, ResponseVo.class, params);
        return responseVo;
    }

    /**
     * 加密传输时使用
     * @param url
     * @param params   加密后数据
     * @return
     */
    public ResponseVo getForResponseVo(String url, String params){
        ResponseVo responseVo = restTemplate.getForObject(url, ResponseVo.class, params);
        return responseVo;
    }

    /**
     *  get方式调用微服务，传递调用的Rest风格url和参数
     * 返回 指定对象 T
     * @param url  请求微服务的Restful  url
     * @param clazz
     * @param params  Get请求参数
     * @param <T>  指定的返回类型
     * @return
     */
    public <T> T getForObj(String url, Class<T> clazz , Map<String, ?> params){
        T response =  restTemplate.getForObject(url, clazz, params);
        return response;
    }

    /**
     * 加密传输时使用
     * @param url
     * @param clazz
     * @param params  加密后数据
     * @param <T>
     * @return
     */
    public <T> T getForObj(String url, Class<T> clazz , String params){
        T response =  restTemplate.getForObject(url, clazz, params);
        return response;
    }

    /**
     * 无参的Get调用
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getForObj(String url, Class<T> clazz){
        T response =  restTemplate.getForObject(url, clazz);
        return response;
    }


    /**
     * Post方式调微服务，
     * 返回ResponseVo 对象
     * @param url
     * @param params
     * @return
     */
    public ResponseVo postForResponseVo(String url, Map<String, ?> params){
        ResponseVo responseVo = restTemplate.postForObject(url,null, ResponseVo.class, params);
        return responseVo;
    }

    /**
     * 加密传输时使用
     * @param url
     * @param params  加密后数据
     * @return
     */
    public ResponseVo postForResponseVo(String url, String params){
        ResponseVo responseVo = restTemplate.postForObject(url,null, ResponseVo.class, params);
        return responseVo;
    }

    /**
     * Post方式调微服务，
     * 返回 指定对象
     * @param url
     * @param clazz
     * @param params
     * @param <T>    指定的返回对象类型
     * @return
     */
    public <T> T postForObj(String url,Class<T> clazz, Map<String, ?> params){
        T response =  restTemplate.postForObject(url, null, clazz, params);
        return response;
    }

    /**
     * 加密传输时使用
     * @param url
     * @param clazz
     * @param params  加密后数据
     * @param <T>    指定的返回对象类型
     * @return
     */
    public <T> T postForObj(String url,Class<T> clazz, String params){
        T response =  restTemplate.postForObject(url, null, clazz, params);
        return response;
    }

    /**
     * 无参的Post调用
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T postForObj(String url,Class<T> clazz){
        T response =  restTemplate.postForObject(url, null, clazz);
        return response;
    }
}
