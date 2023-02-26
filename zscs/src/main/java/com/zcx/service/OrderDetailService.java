package com.zcx.service;


import com.zcx.pojo.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetail> selectByOrderId(Long orderId);

}
