package com.jay.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hetiewei on 2017/2/23.
 */
@Controller
@RequestMapping("/home")
public class IndexController {

    @RequestMapping("/index.html")
    public String index(){
        System.out.println("index");
        return "index";
    }
}
