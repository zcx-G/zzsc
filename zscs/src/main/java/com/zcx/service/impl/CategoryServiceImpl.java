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

    @Override  //分页查询
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

    @Override  //添加分类
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

    @Override  //修改分类
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

    @Override  //删除分类
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

    @Override  //查询所有分类
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
