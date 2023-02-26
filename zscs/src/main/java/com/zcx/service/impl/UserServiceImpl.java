package com.zcx.service.impl;

import com.zcx.mapper.UserMapper;
import com.zcx.pojo.Root;
import com.zcx.pojo.User;
import com.zcx.service.UserService;
import com.zcx.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserServiceImpl implements UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public User select(String phone) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        return mapper.select(phone);
    }

    @Override
    public int add(String phone) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int i = mapper.add(phone);
        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    @Override
    public Root login(String username){
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        Root root = mapper.selectRoot(username);

        sqlSession.close();//释放资源
        return root;
    }
}