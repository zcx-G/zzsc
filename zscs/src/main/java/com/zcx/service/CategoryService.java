package com.zcx.service;


import com.zcx.pojo.Category;
import com.zcx.pojo.PageBean;
import com.zcx.pojo.Return;

import java.util.List;

public interface CategoryService {


    Return<PageBean> selectByPage(int currentPage, int pageSize);

    int add(Category category);

    int update(Category category);

    int delete(Long id);

    List<Category> list();
}
