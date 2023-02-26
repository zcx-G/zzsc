package com.zcx.service.impl;



import com.zcx.mapper.GoodMapper;
import com.zcx.pojo.Good;
import com.zcx.pojo.PageBean;
import com.zcx.pojo.Return;
import com.zcx.service.GoodService;
import com.zcx.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class GoodServiceImpl implements GoodService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Good> list() {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        List<Good> goods = mapper.list();
        sqlSession.close();
        return goods;
    }

    @Override
    public List<Good> categoryList(String categoryName) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        List<Good> goods = mapper.categoryList(categoryName);
        sqlSession.close();
        return goods;
    }

    @Override
    public Good selectById(int id) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        Good good = mapper.selectById(id);
        sqlSession.close();
        return good;
    }

    @Override
    public Return<PageBean> selectGoodPage(int currentPage, int pageSize, String name) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);

        int begin = (currentPage-1) * pageSize;
        int size = pageSize;
        if (name!=null && name!="")
              name = "%"+name+"%";
        //调用方法

        List<Good> rows = mapper.selectGoodPage(begin, size,name);
        int count = mapper.selectCount(name);

        PageBean<Good> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setCount(count);

        sqlSession.close();//释放资源
        return Return.success(pageBean);
    }

    @Override
    public int add(Good good) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取CategoryMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);

        //调用方法
        int i = mapper.add(good);

        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    public int update(Good good) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取CategoryMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);

        //调用方法
        int i = mapper.update(good);

        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    @Override
    public int delete(int[] ids) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取CategoryMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //调用方法
        int i = mapper.delete(ids);

        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    @Override
    public int status(int[] ids,int status) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //调用方法
        int i = mapper.status(ids,status);

        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }
}
