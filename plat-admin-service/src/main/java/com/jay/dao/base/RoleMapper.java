package com.jay.dao.base;

import com.jay.bean.base.Role;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 查询 某个用户拥有的角色
     * @param id
     * @return
     */
    Set<Role> findByUserId(Long id);
}