package com.zcx.service;


import com.zcx.pojo.Good;
import com.zcx.pojo.PageBean;
import com.zcx.pojo.Return;

import java.util.List;

public interface GoodService {

    //查询当前分类下的商品
    List<Good> categoryList(String categoryName);

    //分页条件查询
    Return<PageBean> selectGoodPage(int page, int pageSize,String name);

    Good selectById(int id);   //根据ID查询

    List<Good> list();        //查询所有商品

    int add(Good good);        //添加商品

    int update(Good good);     //修改商品

    int delete(int[] ids);     //删除商品

    int status(int[] ids,int status);   //修改商品状态


}
