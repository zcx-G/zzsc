package com.zcx.service;


import com.zcx.pojo.Good;
import com.zcx.pojo.PageBean;
import com.zcx.pojo.Return;

import java.util.List;

public interface GoodService {
    List<Good> list();

    List<Good> categoryList(String categoryName);

    Good selectById(int id);

    Return<PageBean> selectGoodPage(int page, int pageSize,String name);

    int add(Good good);

    int update(Good good);

    int delete(int[] ids);

    int status(int[] ids,int status);


}
