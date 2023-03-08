package com.zcx.service.impl;


import com.zcx.mapper.ShoppingCartMapper;
import com.zcx.pojo.ShoppingCart;
import com.zcx.service.ShoppingCartService;
import com.zcx.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ShoppingCartServiceImpl implements ShoppingCartService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override  //清空当前用户购物车
    public int deleteAll(Long userId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);

        int i = mapper.deleteAll(userId);
        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    @Override  //删除购物车中一个商品
    public int deleteOne(Integer goodId, Long userId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);

        int i = mapper.deleteOne(goodId,userId);
        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    @Override  //查询当前用户购物车中所有商品
    public List<ShoppingCart> list(Long userId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);

        List<ShoppingCart> list = mapper.list(userId);
        sqlSession.close();//释放资源
        return list;
    }

    @Override  //添加购物车中商品数量
    public int addNumber(Integer goodId, Long userId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);

        int i = mapper.addNumber(goodId,userId);
        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    @Override  //减少购物车中商品数量
    public int subNumber(Integer goodId, Long userId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);

        int i = mapper.subNumber(goodId,userId);
        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    @Override  //根据商品id查询
    public ShoppingCart selectByGoodId(Integer goodId, Long userId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);

        ShoppingCart shoppingCart = mapper.selectByGoodId(goodId, userId);
        sqlSession.close();//释放资源
        return shoppingCart;
    }

    @Override  //向购物车中添加商品
    public int add(ShoppingCart shoppingCart) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);

        int i = mapper.add(shoppingCart);
        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

  }
