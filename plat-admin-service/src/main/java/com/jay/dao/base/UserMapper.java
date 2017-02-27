package com.jay.dao.base;

import com.jay.bean.base.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    /**
     * 登录
     * @param username
     * @return
     */
    User findByUsername(String username);

    List<User> findAll();
}