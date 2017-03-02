package com.jay.service.base;

import com.jay.base.Role;

import java.util.Set;

/**
 * Created by hetiewei on 2017/3/2.
 */
public interface RoleService {
    /**
     * 根据用户id查询角色信息
     * @param id
     * @return
     */
    public Set<Role> findRolesByUserId(Long id);
}
