package com.zcx.service.impl;



import com.zcx.dao.CategoryDao;
import com.zcx.dao.GoodDao;
import com.zcx.pojo.Category;
import com.zcx.pojo.Good;
import com.zcx.pojo.PageBean;
import com.zcx.controller.Return;
import com.zcx.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodDao goodDao;
    @Autowired
    private CategoryDao categoryDao;


    @Override  //查询所有商品
    public List<Good> list() {
        return goodDao.list();
    }

    @Override  //查询当前分类下的商品
    public List<Good> categoryList(Long categoryId) {
        return goodDao.categoryList(categoryId);
    }

    @Override  //根据ID查询
    public Good selectById(int id) {
        return goodDao.selectById(id);
    }

    @Override  //分页条件查询
    public Return selectGoodPage(int page, int pageSize, String name) {
        int begin = (page-1) * pageSize;
        int size = pageSize;
        if (name!=null && name!="")
              name = "%"+name+"%";

        List<Good> rows = goodDao.selectGoodPage(begin,size,name);
        int count = goodDao.selectCount(name);

        PageBean<Good> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setCount(count);

        return Return.success(pageBean);
    }

    @Override  //添加商品
    public boolean add(Good good) {
        Category category = categoryDao.selectById(good.getCategoryId());
        good.setCategoryName(category.getName());
        return goodDao.add(good);
    }

    @Override  //修改商品
    public boolean update(Good good) {
        Category category = categoryDao.selectById(good.getCategoryId());
        System.out.println(category);
        good.setCategoryName(category.getName());
        System.out.println(good);
        return goodDao.update(good);
    }

    @Override  //删除商品
    public boolean delete(int[] ids) {
        //调用方法
        return goodDao.delete(ids);
    }

    @Override  //修改商品状态
    public boolean status(int[] ids,int status) {
        return goodDao.status(ids,status);
    }
}
