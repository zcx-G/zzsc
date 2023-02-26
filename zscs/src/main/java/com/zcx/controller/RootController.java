package com.zcx.controller;


import com.alibaba.fastjson.JSON;
import com.zcx.pojo.Return;
import com.zcx.pojo.Root;
import com.zcx.service.UserService;
import com.zcx.service.impl.UserServiceImpl;
import com.zcx.web.servlet.BaseServlet;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/root/*")
public class RootController extends BaseServlet {
    UserService service = new UserServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将请求体中账号密码封装到实体类
        BufferedReader br = request.getReader();
        String s = br.readLine();
        Root login = JSON.parseObject(s, Root.class);

        //密码md5加密
        String password = login.getPassword();
        password = DigestUtils.md5Hex(password);

        //根据用户名判断是否存在
        Root root = service.login(login.getUsername());
        if (root == null) {
            response.getWriter().write(JSON.toJSONString(Return.error("用户名不存在")));
        }

        if (!root.getPassword().equals(password)) {
            response.getWriter().write(JSON.toJSONString(Return.error("用户名或密码错误")));

        } else {
            request.getSession().setAttribute("root", root.getId());
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(Return.success(root)));
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //清理Session中当前登陆的管理员id
        request.getSession().removeAttribute("root");
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Return.success("退出成功")));
    }
}


