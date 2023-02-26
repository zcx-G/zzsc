package com.zcx.service;




import com.zcx.pojo.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCart selectByGoodId(Long goodId, Long userId);

    int add(ShoppingCart shoppingCart);

    int addNumber(Long goodId, Long userId);

    int subNumber(Long goodId, Long userId);

    int deleteOne(Long goodId, Long userId);

    int deleteAll(Long userId);

    List<ShoppingCart> list(Long userId);
}
