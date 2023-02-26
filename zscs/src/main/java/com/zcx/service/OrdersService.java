package com.zcx.service;


import com.zcx.pojo.Orders;
import com.zcx.pojo.PageBean;
import com.zcx.pojo.Return;

public interface OrdersService {

    int submit(Orders orders);

    Return<PageBean> selectOrderPage(Long userId, int page, int pageSize);

    Return<PageBean> selectAllPage(int page, int pageSize ,String orderId);

    int updateStatus(Long id ,String status);
}
