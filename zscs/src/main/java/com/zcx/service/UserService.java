package com.zcx.service;


import com.zcx.pojo.Root;
import com.zcx.pojo.User;

public interface UserService {

    User select(String phone);

    int add(String phone);

    Root login(String username);
}
