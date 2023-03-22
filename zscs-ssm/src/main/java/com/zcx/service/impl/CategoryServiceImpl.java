package com.zcx.service.impl;


import com.zcx.dao.CategoryDao;
import com.zcx.pojo.Category;
import com.zcx.pojo.PageBean;
import com.zcx.controller.Return;
import com.zcx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override  //分页查询
    public Return selectByPage(int page, int pageSize) {
        int begin = (page-1) * pageSize;
        int size = pageSize;
        //调用方法
        List<Category> rows = categoryDao.selectByPage(begin, size);
        int count = categoryDao.selectTotalCount();
        PageBean<Category> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setCount(count);

        return Return.success(pageBean);
    }

    @Override  //添加分类
    public int add(Category category) {
        return categoryDao.add(category);
    }

    @Override  //修改分类
    public int update(Category category) {
        return categoryDao.update(category);
    }

    @Override  //删除分类
    public int delete(Long id) {
        return categoryDao.delete(id);
    }

    @Override  //查询所有分类
    public List<Category> list() {
        return categoryDao.list();
    }
}
