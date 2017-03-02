package com.jay.controller.base;

import com.jay.bean.base.Resource;
import com.jay.dao.base.ResourceMapper;
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
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 查询某个角色可访问的资源
     * @param id
     * @return
     */
    @GetMapping("/role/{id}")
    public Set<Resource> findResourceByRoleId(@PathVariable("id") Long id){
        return resourceMapper.findByRoleId(id);
    }

}
