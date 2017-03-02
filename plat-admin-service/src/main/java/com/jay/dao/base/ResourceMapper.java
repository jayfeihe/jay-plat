package com.jay.dao.base;

import com.jay.bean.base.Resource;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    /**
     * 查询某个角色所拥有的资源
     * @param id
     * @return
     */
    Set<Resource> findByRoleId(Long id);
}