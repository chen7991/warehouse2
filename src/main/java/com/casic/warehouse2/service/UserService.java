package com.casic.warehouse2.service;

import com.casic.warehouse2.bean.User;
import com.casic.warehouse2.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User getUserByLoginNameAndPassword(String loginname,String pwd){
        return userDao.getUserByLoginNameAndPassword(loginname, pwd);
    }
}
