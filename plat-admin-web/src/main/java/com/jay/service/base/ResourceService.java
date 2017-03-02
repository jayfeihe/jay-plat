package com.jay.service.base;

import com.jay.base.Resource;

import java.util.Set;

/**
 * Created by hetiewei on 2017/3/2.
 */
public interface ResourceService {
    /**
     * 查询角色拥有的权限
     * @param id
     * @return
     */
    Set<Resource> findByRoleId(Long id);
}
