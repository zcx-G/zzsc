package com.zcx.mapper;


import com.zcx.pojo.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ShoppingCartMapper {

    @Select("SELECT * FROM shopping_cart WHERE user_id = #{userId} and good_id= #{goodId}")
    @ResultMap("ShoppingCartResultMap")
    ShoppingCart selectByGoodId(@Param("goodId") Integer goodId, @Param("userId") Long userId);
    //根据商品id查询

    @Insert("INSERT INTO shopping_cart( name, image, user_id, good_id, amount, create_time) " +
            "VALUE (#{name},#{image},#{userId},#{goodId},#{amount},#{createTime})")
    int add(ShoppingCart shoppingCart);
    //向购物车中添加商品

    @Update("UPDATE shopping_cart SET number=number+1 WHERE user_id=#{userId} and good_id= #{goodId}")
    int addNumber(@Param("goodId") Integer goodId, @Param("userId") Long userId);
    //添加购物车中商品数量

    @Update("UPDATE shopping_cart SET number=number-1 WHERE user_id=#{userId} and good_id= #{goodId}")
    int subNumber(@Param("goodId") Integer goodId, @Param("userId") Long userId);
    //减少购物车中商品数量

    @Delete("DELETE FROM shopping_cart WHERE user_id=#{userId} and good_id= #{goodId}")
    int deleteOne(@Param("goodId") Integer goodId, @Param("userId") Long userId);
    //删除购物车中一个商品

    @Delete("DELETE FROM shopping_cart WHERE user_id=#{userId}")
    int deleteAll(Long userId);
    //清空当前用户购物车

    @Select("SELECT * FROM shopping_cart WHERE user_id = #{userId} ")
    @ResultMap("ShoppingCartResultMap")
    List<ShoppingCart> list(Long userId);
    //查询当前用户购物车中所有商品


}
