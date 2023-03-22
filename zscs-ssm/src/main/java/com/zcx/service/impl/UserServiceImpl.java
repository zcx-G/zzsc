package com.zcx.service.impl;

import com.zcx.dao.UserDao;
import com.zcx.pojo.Root;
import com.zcx.pojo.User;
import com.zcx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override  //用户查询
    public User userSelect(String phone) {
        return userDao.select(phone);
    }

    @Override  //添加用户
    public boolean add(String phone) {
        return userDao.add(phone);
    }

    @Override  //管理员查询
    public Root rootSelect(String username){
        return userDao.selectRoot(username);
    }
}