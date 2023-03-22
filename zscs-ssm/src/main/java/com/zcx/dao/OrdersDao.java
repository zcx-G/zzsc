package com.zcx.dao;


import com.zcx.pojo.OrderDetail;
import com.zcx.pojo.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {
    @Insert("INSERT INTO " +
            "orders(id, number, status, user_id, address_book_id, order_time, pay_method, amount, remark, phone, address, user_name, consignee) " +
            "VALUE(#{id},#{number},#{status},#{userId},#{addressBookId},#{orderTime},#{payMethod},#{amount},#{remark},#{phone},#{address},#{userName},#{consignee}) ")
    boolean add(Orders orders);
    //添加订单

    @Select("SELECT * FROM orders WHERE user_id = #{userId} LIMIT #{begin},#{size}")
    @ResultMap("ordersResultMap")
    List<Orders> selectOrderPage(@Param("userId") Long userId, @Param("begin") int begin, @Param("size") int size);
    //分页条件查询

    @Select("SELECT COUNT(*) FROM orders WHERE user_id=#{userId}")
    int selectOrderCount(Long userId);
    //用户订单数量

    List<Orders> selectAllPage(@Param("begin") int begin, @Param("size") int size ,@Param("orderId") String orderId);
    //查询所有

    @Select("SELECT COUNT(*) FROM orders")
    int selectCount();
    //所有订单数量

    @Update("UPDATE orders SET status=#{status} WHERE number = #{id}")
    boolean updateStatus(@Param("id") Long id ,@Param("status") String status);
    //状态修改

    boolean addAll(@Param("orderDetails") List<OrderDetail> orderDetails);
    //添加订单详情

    @Select("SELECT * FROM order_detail WHERE order_id = #{orderId}")
    List<OrderDetail> selectByOrderId(Long orderId);
    //根据订单ID查询订单详情
}
