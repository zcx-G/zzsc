package com.zcx.mapper;


import com.zcx.pojo.OrderDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderDetailMapper {

    int addAll(@Param("orderDetails") List<OrderDetail> orderDetails);

    @Select("SELECT * FROM order_detail WHERE order_id = #{orderId}")
    List<OrderDetail> selectByOrderId(Long orderId);
}
