package com.zcx.controller;


import com.alibaba.fastjson.JSON;
import com.zcx.pojo.Return;
import com.zcx.pojo.ShoppingCart;
import com.zcx.service.ShoppingCartService;
import com.zcx.service.impl.ShoppingCartServiceImpl;
import com.zcx.web.servlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/shoppingCart/*")
public class ShoppingCartController extends BaseServlet {
     ShoppingCartService service = new ShoppingCartServiceImpl();

    /**
     * 添加
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //将请求体中数据封装到实体类
        BufferedReader br = request.getReader();
        String s = br.readLine();
        ShoppingCart shoppingCart= JSON.parseObject(s, ShoppingCart.class);

        String time = LocalDateTime.now().toLocalDate() + " " + LocalDateTime.now().toLocalTime();
        shoppingCart.setCreateTime(time);
        Long userId = (Long) request.getSession().getAttribute("user");

        shoppingCart.setUserId(userId);

        ShoppingCart good = service.selectByGoodId(shoppingCart.getGoodId(), shoppingCart.getUserId());
        if (good == null){
            int i = service.add(shoppingCart);
            if (i==1){
                response.getWriter().write(JSON.toJSONString(Return.success("success")));
            }else {
                response.getWriter().write(JSON.toJSONString(Return.error("error")));
            }
        }else {
            int i = service.addNumber(shoppingCart.getGoodId(), shoppingCart.getUserId());
            if (i==1){
                response.getWriter().write(JSON.toJSONString(Return.success("success")));
            }else {
                response.getWriter().write(JSON.toJSONString(Return.error("error")));
            }
        }
    }

    /**
     *减少
     */
    public void sub(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //将请求体中数据封装到实体类
        BufferedReader br = request.getReader();
        String s = br.readLine();
        ShoppingCart shoppingCart= JSON.parseObject(s, ShoppingCart.class);

        Long userId = (Long) request.getSession().getAttribute("user");

        shoppingCart.setUserId(userId);
        System.out.println(shoppingCart);
        int i = service.subNumber(shoppingCart.getGoodId(), shoppingCart.getUserId());
        ShoppingCart good = service.selectByGoodId(shoppingCart.getGoodId(), shoppingCart.getUserId());
        if (good.getNumber()==0)
            service.deleteOne(shoppingCart.getGoodId(), shoppingCart.getUserId());
        if (i==1){
            response.getWriter().write(JSON.toJSONString(Return.success("success")));
        }else {
            response.getWriter().write(JSON.toJSONString(Return.error("error")));
        }
    }

    /**
     *查询所有
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long userId = (Long) request.getSession().getAttribute("user");
        List<ShoppingCart> list = service.list(userId);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Return.success(list)));
    }

    /**
     *清空
     */
    public void clean(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long userId = (Long) request.getSession().getAttribute("user");
        int i = service.deleteAll(userId);
        if (i>=1){
            response.getWriter().write(JSON.toJSONString(Return.success("success")));
        }else {
            response.getWriter().write(JSON.toJSONString(Return.error("error")));
        }
    }
}



