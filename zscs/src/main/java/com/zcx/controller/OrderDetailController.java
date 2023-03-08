package com.zcx.controller;

import com.alibaba.fastjson.JSON;
import com.zcx.pojo.OrderDetail;
import com.zcx.pojo.Return;
import com.zcx.service.OrdersService;
import com.zcx.service.impl.OrdersServiceImpl;
import com.zcx.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/orderDetail/*")
public class OrderDetailController extends BaseServlet {
   private final OrdersService service = new OrdersServiceImpl();

    /**
     * //根据订单ID查询订单详情
     */
    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("id");
        List<OrderDetail> orderDetails = service.selectByOrderId(Long.valueOf(orderId));

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Return.success(orderDetails)));
    }

}