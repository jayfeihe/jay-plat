package com.jay.service.base.impl;

import com.jay.base.User;
import com.jay.service.base.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *调用微服务plat-web-service的接口
 * Created by hetiewei on 2017/2/28.
 */
@Service
public class UserServiceImpl implements UserService{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Rest方式，负载均衡调用微服务
     */
    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @Value("${cloud.plat.admin.user.service}")
    private String userService;

    @Override
    public User findByUsername(String username) {
        return restTemplate.getForObject("http://"+userService +"/user/find/"+username, User.class);
    }

    @Override
    public User loginCheck(String username, String password, String captcha) {
        //1.检查验证码captcha是否正确
        //2.根据username查询到的User信息，匹配用户password
        //3.返回登录信息
        return null;
    }
}
