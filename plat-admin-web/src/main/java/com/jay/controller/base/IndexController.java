package com.jay.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 项目首页
 * Created by hetiewei on 2017/2/23.
 */
@Controller
@RequestMapping("/home")
public class IndexController extends BaseController{

    @RequestMapping("/index.html")
    public String index(Model model){
        System.out.println("index");
        model.addAttribute("username", getUserName());
        return "index";
    }
}
