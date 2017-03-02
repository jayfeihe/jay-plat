package com.jay.controller.cms.user;

import com.jay.service.base.UserService;
import com.jay.vo.base.BaseResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hetiewei on 2017/2/27.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户列表页
     * @return
     */
    @GetMapping("/list")
    public String listPage(){
        return "user/list";
    }

    /**
     * 登录页面
     * @return
     */
    @GetMapping("/login")
    public String loginPage(){

        return "user/login";
    }

    /**
     * 登录校验
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public BaseResponseVo login(String username, String password){

        return null;
    }

    /**
     * 测试列表数据
     * @param order
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @GetMapping("/data")
    public List<Data> getAll(String order, int offset, int limit){
        List<Data> list = new ArrayList<>();
        for (int i=0;i<20;i++){
            list.add(new Data(i, "item "+i, "$"+i));
        }
        if (order.equals("desc")) {
            Collections.reverse(list);
        }
        return list.subList(offset, limit);
    }
}

class Data{
    private int id;
    private String name;
    private String price;

    public Data() {
    }

    public Data(int id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}