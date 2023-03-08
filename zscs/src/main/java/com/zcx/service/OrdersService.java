package com.zcx.service;


import com.zcx.pojo.OrderDetail;
import com.zcx.pojo.Orders;
import com.zcx.pojo.PageBean;
import com.zcx.pojo.Return;

import java.util.List;

public interface OrdersService {

    //订单提交
    int submit(Orders orders);

    //分页查询
    Return<PageBean> selectOrderPage(Long userId, int page, int pageSize);

    //查询所有
    Return<PageBean> selectAllPage(int page, int pageSize ,String orderId);

    //修改状态
    int updateStatus(Long id ,String status);

    //根据订单ID查询订单详情
    List<OrderDetail> selectByOrderId(Long orderId);
}
