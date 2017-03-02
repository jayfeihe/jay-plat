package com.jay.service.base.impl;

import com.jay.base.User;
import com.jay.service.base.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *调用微服务plat-web-service的接口
 * Created by hetiewei on 2017/2/28.
 */
@Service
public class UserServiceImpl extends BaseCloudService implements UserService{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public User findByUsername(String username) {
        return getForObj("http://"+cloudService +"/user/find/"+username, User.class);
    }

    @Override
    public User loginCheck(String username, String password, String captcha) {
        //1.检查验证码captcha是否正确
        //2.根据username查询到的User信息，匹配用户password
        //3.返回登录信息
        return null;
    }

}
