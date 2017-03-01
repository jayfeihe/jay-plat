package com.jay.service.base;

import com.jay.base.User;

/**
 * Created by hetiewei on 2017/2/28.
 */
public interface UserService {
    /**
     * 按用户登录名查询
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 登录校验
     * @param username
     * @param password
     * @param captcha
     * @return
     */
    public User loginCheck(String username, String password, String captcha);

}
