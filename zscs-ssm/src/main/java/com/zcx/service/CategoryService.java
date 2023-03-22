package com.zcx.service;


import com.zcx.pojo.Category;
import com.zcx.pojo.PageBean;
import com.zcx.controller.Return;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CategoryService {

    //分页查询
    Return selectByPage(int page, int pageSize);

    int add(Category category);     //添加分类

    int update(Category category);  //修改分类

    int delete(Long id);            //删除分类

    List<Category> list();          //查询所有分类
}
