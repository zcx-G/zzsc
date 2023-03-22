package com.zcx.service;


import com.zcx.config.SpringConfig;
import com.zcx.dao.UserDao;
import com.zcx.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class Test1 {
    @Autowired
    private UserService userService;


    @Test
    public void CategoryTest(){
        User user = userService.userSelect("19974074351");
        System.out.println(user);
    }

}
