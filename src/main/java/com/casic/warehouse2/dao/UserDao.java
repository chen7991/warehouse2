package com.casic.warehouse2.dao;

import com.casic.warehouse2.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 用户Dao
 */
@Component
public interface UserDao {
    public User getUserByLoginNameAndPassword(@Param("loginname") String loginname, @Param("pwd") String pwd);
}
