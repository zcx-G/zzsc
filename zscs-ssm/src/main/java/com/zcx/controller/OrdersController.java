package com.zcx.controller;


import com.zcx.pojo.OrderDetail;
import com.zcx.pojo.Orders;
import com.zcx.pojo.PageBean;
import com.zcx.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController{
    @Autowired
    private OrdersService ordersService;

    /**
     * 订单提交
     */
    @PostMapping
    public Return submit(@RequestBody Orders orders , HttpServletRequest request){
        Object userId = request.getSession().getAttribute("user");
        orders.setUserId((Long) userId);
        boolean flag = ordersService.submit(orders);
        if (flag){
            return Return.success("success");
        }else {
            return Return.error("error");
        }

    }

    /**
     *分页查询
     */
    @GetMapping
    public Return page(@RequestParam int page,@RequestParam int pageSize , HttpServletRequest request){
        Long userId = (Long) request.getSession().getAttribute("user");
        PageBean<Orders> pageBean = ordersService.selectOrderPage(userId, page, pageSize);
        return Return.success(pageBean);
    }

    /**
     *根据订单ID分页查询
     */
    @GetMapping("/all")
    public Return all(@RequestParam int page,@RequestParam int pageSize ,@RequestParam String orderId){

        PageBean<Orders> pageBean = ordersService.selectAllPage(page, pageSize, orderId);
        return Return.success(pageBean);
     }

    /**
     *修改状态
     */
    @PutMapping
    public Return status(@RequestParam String status,@RequestParam Long id){
        boolean flag = ordersService.updateStatus(id, status);
        if (flag){
            return Return.success("success");
        }else {
            return Return.error("error");
        }
    }

    /**
     * 根据订单ID查询订单详情
     */
    @GetMapping("/{id}")
    public Return selectById(@PathVariable Long id){
        System.out.println(id);
        List<OrderDetail> orderDetails = ordersService.selectByOrderId(id);
        return Return.success(orderDetails);
    }
}


