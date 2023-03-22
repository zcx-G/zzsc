package com.zcx.service;


import com.zcx.pojo.OrderDetail;
import com.zcx.pojo.Orders;
import com.zcx.pojo.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface OrdersService {

    //订单提交
    boolean submit(Orders orders);

    //分页查询
    PageBean<Orders> selectOrderPage(Long userId, int page, int pageSize);

    //查询所有
    PageBean<Orders> selectAllPage(int page, int pageSize ,String orderId);

    //修改状态
    boolean updateStatus(Long id ,String status);

    //根据订单ID查询订单详情
    List<OrderDetail> selectByOrderId(Long orderId);
}
