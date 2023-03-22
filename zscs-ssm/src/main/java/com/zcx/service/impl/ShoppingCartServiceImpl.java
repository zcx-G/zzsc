package com.zcx.service.impl;

import com.zcx.dao.ShoppingCartDao;
import com.zcx.pojo.ShoppingCart;
import com.zcx.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Override  //清空当前用户购物车
    public boolean deleteAll(Long userId) {
        return shoppingCartDao.deleteAll(userId);
    }

    @Override  //删除购物车中一个商品
    public boolean deleteOne(Integer goodId, Long userId) {
        return shoppingCartDao.deleteOne(goodId,userId);
    }

    @Override  //查询当前用户购物车中所有商品
    public List<ShoppingCart> list(Long userId) {
        return shoppingCartDao.list(userId);
    }

    @Override  //添加购物车中商品数量
    public boolean addNumber(Integer goodId, Long userId) {
        return shoppingCartDao.addNumber(goodId,userId);
    }

    @Override  //减少购物车中商品数量
    public boolean subNumber(Integer goodId, Long userId) {
        return shoppingCartDao.subNumber(goodId,userId);
    }

    @Override  //根据商品id查询
    public ShoppingCart selectByGoodId(Integer goodId, Long userId) {
        return shoppingCartDao.selectByGoodId(goodId, userId);
    }

    @Override  //向购物车中添加商品
    public boolean add(ShoppingCart shoppingCart) {
        return shoppingCartDao.add(shoppingCart);
    }

  }
