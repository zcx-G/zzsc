package com.zcx.service;


import com.zcx.pojo.Root;
import com.zcx.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {

    User userSelect(String phone);      //用户查询

    boolean add(String phone);          //添加用户

    Root rootSelect(String username);   //管理员查询
}
