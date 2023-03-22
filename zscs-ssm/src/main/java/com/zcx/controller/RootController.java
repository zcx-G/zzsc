package com.zcx.controller;


import com.zcx.pojo.Root;
import com.zcx.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/roots")
public class RootController{
    @Autowired
    private UserService userService;
    /**
     * 登录
     */
    @PostMapping
    public Return login(@RequestBody Root login , HttpServletRequest request){
        //密码md5加密
        String password = login.getPassword();
        password = DigestUtils.md5Hex(password);

        //根据用户名判断是否存在
        Root root = userService.rootSelect(login.getUsername());
        if (root == null) {
            return Return.error("用户名不存在");
        }

        if (!root.getPassword().equals(password)) {
            return Return.error("用户名或密码错误");
        } else {
            request.getSession().setAttribute("root", root.getId());
            return Return.success(root);
        }
    }

    /**
     * 登出
     */
    @GetMapping
    public Return logout(HttpServletRequest request){
        //清理Session中当前登陆的管理员id
        request.getSession().removeAttribute("root");
        return Return.success("退出成功");
    }
}


