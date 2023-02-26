package com.zcx.mapper;


import com.zcx.pojo.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersMapper {
    @Insert("INSERT INTO " +
            "orders(id, number, status, user_id, address_book_id, order_time, pay_method, amount, remark, phone, address, user_name, consignee) " +
            "VALUE(#{id},#{number},#{status},#{userId},#{addressBookId},#{orderTime},#{payMethod},#{amount},#{remark},#{phone},#{address},#{userName},#{consignee}) ")
    int add(Orders orders);

    @Select("SELECT * FROM orders WHERE user_id = #{userId} LIMIT #{begin},#{size}")
    @ResultMap("ordersResultMap")
    List<Orders> selectOrderPage(@Param("userId") Long userId, @Param("begin") int begin, @Param("size") int size);


    List<Orders> selectAllPage(@Param("begin") int begin, @Param("size") int size ,@Param("orderId") String orderId);

    @Select("SELECT COUNT(*) FROM orders")
    int selectCount();

    @Update("UPDATE orders SET status=#{status} WHERE number = #{id}")
    int updateStatus(@Param("id") Long id ,@Param("status") String status);
}
