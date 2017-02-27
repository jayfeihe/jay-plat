package com.jay.controller.base;

import com.jay.bean.base.User;
import com.jay.dao.base.UserMapper;
import com.jay.util.encrypt.Md5SaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户操作的接口
 * Created by hetiewei on 2017/2/27.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public User login(String username, String password){
        User user = userMapper.findByUsername(username);
        if (user.getPassword().equals(Md5SaltUtil.verify(password, user.getPassword()))){
            return user;
        }
        return null;
    }

    /**
     * 创建用户
     * @param user
     * @return
     */
    public User add(User user){
        //获取随机盐值，并对密码进行MD5加密后，信息保存到数据
        user.setSalt(Md5SaltUtil.getSalt(16));
        user.setPassword(Md5SaltUtil.generate(user.getPassword()));
        userMapper.insertSelective(user);
        return user;
    }

    public static void main(String args[]){
        String salt = Md5SaltUtil.getSalt(16);
        String salt2 = Md5SaltUtil.getSalt(16);
        String password = Md5SaltUtil.generate("admin");
        System.out.println(salt);
        System.out.println(password);
        System.out.println(Md5SaltUtil.verify("admin",password));
    }
}
