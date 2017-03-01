package com.jay.controller.base;

import com.jay.bean.base.User;
import com.jay.dao.base.UserMapper;
import com.jay.util.encrypt.EncryptAndDecryptUtils;
import com.jay.util.encrypt.Md5SaltUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @GetMapping("/find/{username}")
    public User findByUsername(@PathVariable("username") String username){
        return userMapper.findByUsername(username);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login/{username}/{password}")
    public User login(@PathVariable("username") String username, @PathVariable("password") String password){
        User user = userMapper.findByUsername(username);
        //检测与含salt盐值的密码是否匹配
        if (Md5SaltUtil.verify(password, user.getPassword())){
            return user;
        }
        return null;
    }

    /**
     * 创建用户
     * @param user
     * @return
     */
    @GetMapping("/add")
    public User add(User user){
        //获取随机盐值，并对密码进行MD5加密后，信息保存到数据
        String salt = Md5SaltUtil.getSalt(16);
        user.setSalt(salt);
        user.setPassword(EncryptAndDecryptUtils.md5Encrypt(user.getPassword()));
        userMapper.insertSelective(user);
        return user;
    }

    @GetMapping("/list")
    public List<User> getUsers(){
        return userMapper.findAll();
    }

    public static void main(String args[]){
//        String salt = Md5SaltUtil.getSalt(16);
//        String salt2 = Md5SaltUtil.getSalt(16);
//        String password = Md5SaltUtil.generate("admin");
//        System.out.println(salt);
//        System.out.println(password);
//        System.out.println(Md5SaltUtil.verify("admin",password));

        System.out.println(Md5SaltUtil.verify("test1","f8694f03ee1f88a64931b35b05603a37ac7259d411c3e251"));
    }
}
