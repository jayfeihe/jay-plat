package com.jay.controller.base;

import com.jay.bean.base.Role;
import com.jay.dao.base.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by hetiewei on 2017/3/2.
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询某个用户拥有的角色
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public Set<Role> findByUserId(@PathVariable("id") Long id){
        return roleMapper.findByUserId(id);
    }
}
