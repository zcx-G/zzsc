package com.zcx.service.impl;


import com.zcx.mapper.OrderDetailMapper;
import com.zcx.pojo.OrderDetail;
import com.zcx.service.OrderDetailService;
import com.zcx.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<OrderDetail> selectByOrderId(Long orderId) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        OrderDetailMapper mapper = sqlSession.getMapper(OrderDetailMapper.class);

        return mapper.selectByOrderId(orderId);
    }
}
