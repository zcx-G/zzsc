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

    @Override
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

    @Override
    public int deleteOne(Long goodId, Long userId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);

        int i = mapper.deleteOne(goodId,userId);
        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    @Override
    public List<ShoppingCart> list(Long userId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);

        List<ShoppingCart> list = mapper.list(userId);
        sqlSession.close();//释放资源
        return list;
    }

    @Override
    public int addNumber(Long goodId, Long userId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);

        int i = mapper.addNumber(goodId,userId);
        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    @Override
    public int subNumber(Long goodId, Long userId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);

        int i = mapper.subNumber(goodId,userId);
        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    @Override
    public ShoppingCart selectByGoodId(Long goodId, Long userId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        ShoppingCartMapper mapper = sqlSession.getMapper(ShoppingCartMapper.class);

        ShoppingCart shoppingCart = mapper.selectByGoodId(goodId, userId);
        sqlSession.close();//释放资源
        return shoppingCart;
    }

    @Override
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
