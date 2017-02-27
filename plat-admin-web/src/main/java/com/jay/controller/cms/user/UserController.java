package com.jay.controller.cms.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hetiewei on 2017/2/27.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/list")
    public String list(){
        return "user/list";
    }
}
