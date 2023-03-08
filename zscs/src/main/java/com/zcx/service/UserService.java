package com.zcx.service;


import com.zcx.pojo.Root;
import com.zcx.pojo.User;

public interface UserService {

    User userSelect(String phone);      //用户查询

    int add(String phone);              //添加用户

    Root rootSelect(String username);  //管理员查询
}
