package com.jay.service.base.impl;

import com.jay.base.Resource;
import com.jay.service.base.ResourceService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by hetiewei on 2017/3/2.
 */
@Service
public class ResourceServiceImpl extends BaseCloudService implements ResourceService {
    @Override
    public Set<Resource> findByRoleId(Long id) {
        return getForObj("http://"+cloudService +"/resource/role/"+id, Set.class);
    }
}
