package com.jay.service.base.impl;

import com.jay.base.Role;
import com.jay.service.base.RoleService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by hetiewei on 2017/3/2.
 */
@Service
public class RoleServiceImpl extends BaseCloudService implements RoleService {

    @Override
    public Set<Role> findRolesByUserId(Long id) {
        return getForObj("http://"+cloudService +"/role/find/user/"+id, Set.class);
    }
}
