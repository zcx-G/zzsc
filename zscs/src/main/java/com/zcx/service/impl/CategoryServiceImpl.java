package com.zcx.service.impl;


import com.zcx.mapper.CategoryMapper;
import com.zcx.pojo.Category;
import com.zcx.pojo.PageBean;
import com.zcx.pojo.Return;
import com.zcx.service.CategoryService;
import com.zcx.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public Return<PageBean> selectByPage(int currentPage, int pageSize) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取BrandMapper
        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);

        int begin = (currentPage-1) * pageSize;
        int size = pageSize;
        //调用方法
        List<Category> rows = mapper.selectByPage(begin, size);
        int count = mapper.selectTotalCount();

        PageBean<Category> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setCount(count);

        sqlSession.commit();
        sqlSession.close();//释放资源
        return Return.success(pageBean);
    }

    @Override
    public int add(Category category) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取CategoryMapper
        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
        //调用方法
        int i = mapper.add(category);

        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    public int update(Category category) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取CategoryMapper
        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
        //调用方法
        int i = mapper.update(category);

        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    @Override
    public int delete(Long id) {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取CategoryMapper
        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
        //调用方法
        int i = mapper.delete(id);

        sqlSession.commit();//提交事务
        sqlSession.close();//释放资源
        return i;
    }

    @Override
    public List<Category> list() {
        //获取SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取BrandMapper
        CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);

        //调用方法
        List<Category> rows = mapper.list();

        sqlSession.commit();
        sqlSession.close();//释放资源
        return rows;
    }
}
