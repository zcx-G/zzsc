package com.zcx.controller;


import com.alibaba.fastjson.JSON;
import com.zcx.pojo.Orders;
import com.zcx.pojo.PageBean;
import com.zcx.pojo.Return;
import com.zcx.service.OrdersService;
import com.zcx.service.impl.OrdersServiceImpl;
import com.zcx.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/order/*")
public class OrdersController extends BaseServlet {
    private final OrdersService service = new OrdersServiceImpl();

    /**
     * 订单提交
     */
    public void submit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将请求体中数据封装到实体类
        BufferedReader br = request.getReader();
        String s = br.readLine();
        Orders orders = JSON.parseObject(s, Orders.class);
        Object userId = request.getSession().getAttribute("user");
        orders.setUserId((Long) userId);
        int i = service.submit(orders);
        if (i==1){
            response.getWriter().write(JSON.toJSONString(Return.success("success")));
        }else {
            response.getWriter().write(JSON.toJSONString(Return.error("error")));
        }

    }

    /**
     *分页查询
     */
    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        Long userId = (Long) request.getSession().getAttribute("user");

        Return<PageBean> pageBeanReturn = service.selectOrderPage(userId, page, pageSize);
        System.out.println(pageBeanReturn);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(pageBeanReturn));
    }

    /**
     *查询所有
     */
    public void all(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String orderId = request.getParameter("number");


        System.out.println( orderId);
        Return<PageBean> pageBeanReturn = service.selectAllPage(page, pageSize,orderId);
        System.out.println(pageBeanReturn);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(pageBeanReturn));
     }

    /**
     *修改状态
     */
    public void status(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("status");
        String orderId = request.getParameter("id");

        System.out.println(status);
        System.out.println(orderId);

        int i = service.updateStatus(Long.valueOf(orderId), status);
        if (i>=1){
            response.getWriter().write(JSON.toJSONString(Return.success("success")));
        }else {
            response.getWriter().write(JSON.toJSONString(Return.error("error")));
        }
    }
}


