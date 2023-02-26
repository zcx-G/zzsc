package com.zcx.mapper;


import com.zcx.pojo.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ShoppingCartMapper {

    @Select("SELECT * FROM shopping_cart WHERE user_id = #{userId} and good_id= #{goodId}")
    @ResultMap("ShoppingCartResultMap")
    ShoppingCart selectByGoodId(@Param("goodId") Long goodId, @Param("userId") Long userId);

    @Insert("INSERT INTO shopping_cart( name, image, user_id, good_id, amount, create_time) " +
            "VALUE (#{name},#{image},#{userId},#{goodId},#{amount},#{createTime})")
    int add(ShoppingCart shoppingCart);

    @Update("UPDATE shopping_cart SET number=number+1 WHERE user_id=#{userId} and good_id= #{goodId}")
    int addNumber(@Param("goodId") Long goodId, @Param("userId") Long userId);

    @Update("UPDATE shopping_cart SET number=number-1 WHERE user_id=#{userId} and good_id= #{goodId}")
    int subNumber(@Param("goodId") Long goodId, @Param("userId") Long userId);

    @Delete("DELETE FROM shopping_cart WHERE user_id=#{userId} and good_id= #{goodId}")
    int deleteOne(@Param("goodId") Long goodId, @Param("userId") Long userId);

    @Delete("DELETE FROM shopping_cart WHERE user_id=#{userId}")
    int deleteAll(Long userId);

    @Select("SELECT * FROM shopping_cart WHERE user_id = #{userId} ")
    @ResultMap("ShoppingCartResultMap")
    List<ShoppingCart> list(Long userId);


}
