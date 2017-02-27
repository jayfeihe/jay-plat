package com.jay.controller.cms.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户登录相关
 * Created by hetiewei on 2017/2/27.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * 登录页
     * @return
     */
    @GetMapping(value="")
    public String page(){
        return "user/login";
    }

    /**
     * 登录检查
     * @param username
     * @param password
     * @param captcha  验证码
     * @return
     */
    @PostMapping(value="/check")
    public String check(String username, String password, String captcha){
        return "success";
    }


}
