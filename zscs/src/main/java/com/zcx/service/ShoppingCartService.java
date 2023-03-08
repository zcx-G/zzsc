package com.zcx.service;

import com.zcx.pojo.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCart selectByGoodId(Integer goodId, Long userId);  //根据商品id查询

    int add(ShoppingCart shoppingCart);                        //向购物车中添加商品

    int addNumber(Integer goodId, Long userId);                //添加购物车中商品数量

    int subNumber(Integer goodId, Long userId);                //减少购物车中商品数量

    int deleteOne(Integer goodId, Long userId);                //删除购物车中一个商品

    int deleteAll(Long userId);                                //清空当前用户购物车

    List<ShoppingCart> list(Long userId);                      //查询当前用户购物车中所有商品
}
