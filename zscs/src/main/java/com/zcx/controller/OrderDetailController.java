package com.zcx.controller;

import com.alibaba.fastjson.JSON;
import com.zcx.pojo.OrderDetail;
import com.zcx.pojo.Return;
import com.zcx.service.OrderDetailService;
import com.zcx.service.impl.OrderDetailServiceImpl;
import com.zcx.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/orderDetail/*")
public class OrderDetailController extends BaseServlet {
    private final OrderDetailService service = new OrderDetailServiceImpl();


    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("id");
        List<OrderDetail> orderDetails = service.selectByOrderId(Long.valueOf(orderId));

        System.out.println(orderDetails);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Return.success(orderDetails)));
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void status(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}