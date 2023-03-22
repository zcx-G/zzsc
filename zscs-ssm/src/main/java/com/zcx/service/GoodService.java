package com.zcx.service;


import com.zcx.pojo.Good;
import com.zcx.pojo.PageBean;
import com.zcx.controller.Return;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface GoodService {

    //查询当前分类下的商品
    List<Good> categoryList(Long categoryId);

    //分页条件查询
    Return selectGoodPage(int page, int pageSize,String name);

    Good selectById(int id);   //根据ID查询

    List<Good> list();        //查询所有商品

    boolean add(Good good);        //添加商品

    boolean update(Good good);     //修改商品

    boolean delete(int[] ids);     //删除商品

    boolean status(int[] ids,int status);   //修改商品状态

}
