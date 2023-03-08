package com.zcx.controller;


import com.alibaba.fastjson.JSON;
import com.zcx.pojo.Return;
import com.zcx.pojo.User;
import com.zcx.service.UserService;
import com.zcx.service.impl.UserServiceImpl;
import com.zcx.utils.CheckCodeUtil;
import com.zcx.utils.SMSUtils;
import com.zcx.web.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Map;

@WebServlet("/user/*")
public class UserController extends BaseServlet {

    UserService service = new UserServiceImpl();

    /**
     * 验证码
     */
    public void msg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("移动端验证码");

        String code = CheckCodeUtil.generateVerifyCode(6,"0123456789");
        System.out.println(code);
        SMSUtils.sendMessage("19974074351",code);
        //将请求体中账号密码封装到实体类
        BufferedReader br = request.getReader();
        String s = br.readLine();
        User login = JSON.parseObject(s, User.class);

        request.getSession().setAttribute("code", code);
    }

    /**
     * 登录
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json;charset=utf-8");
        System.out.println("移动端登录");
        BufferedReader br = request.getReader();
        String s = br.readLine();
        Map login = JSON.parseObject(s, Map.class);
        System.out.println(login);

        String phone = login.get("phone").toString();
        String code = login.get("code").toString();

        Object sessionCode = request.getSession().getAttribute("code");
        if (sessionCode!=null && sessionCode.equals(code)) {
            User user = service.userSelect(phone);
            if (user == null){
                int i = service.add(phone);
                System.out.println(i);
            }
            user = service.userSelect(phone);
            System.out.println(user);
            request.getSession().setAttribute("user",user.getId());
            response.getWriter().write(JSON.toJSONString(Return.success(user)));

        }else
            response.getWriter().write(JSON.toJSONString(Return.error("手机号或验证码错误")));
    }

    /**
     * 登出
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("移动端退出登录");
        //清理Session中当前登陆的用户id
        request.getSession().removeAttribute("user");
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Return.success("退出成功")));
    }
}


