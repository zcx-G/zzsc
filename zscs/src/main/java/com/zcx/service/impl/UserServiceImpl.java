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

    @Override  //用户查询
    public User userSelect(String phone) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.select(phone);
        sqlSession.close();//释放资源
        return user;
    }

    @Override  //添加用户
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

    @Override  //管理员查询
    public Root rootSelect(String username){
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Root root = mapper.selectRoot(username);

        sqlSession.close();//释放资源
        return root;
    }
}