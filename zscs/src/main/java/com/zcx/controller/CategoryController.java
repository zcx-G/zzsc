package com.zcx.controller;


import com.alibaba.fastjson.JSON;
import com.zcx.pojo.Category;
import com.zcx.pojo.PageBean;
import com.zcx.pojo.Return;
import com.zcx.service.CategoryService;
import com.zcx.service.impl.CategoryServiceImpl;
import com.zcx.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@WebServlet("/category/*")
public class CategoryController extends BaseServlet {
    private final CategoryService service = new CategoryServiceImpl();

    /**
     * 分页查询
     */
    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _page = request.getParameter("page");
        String _pageSize = request.getParameter("pageSize");

        int page=Integer.parseInt(_page);
        int pageSize=Integer.parseInt(_pageSize);

        Return<PageBean> categoryReturn= service.selectByPage(page, pageSize);
        String jsonString = JSON.toJSONString(categoryReturn);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 添加
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //将请求体中数据封装到实体类
        BufferedReader br = request.getReader();
        String s = br.readLine();
        Category category = JSON.parseObject(s, Category.class);

        category.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

        Integer root = (Integer) request.getSession().getAttribute("root");
        category.setUpdateUser(root);

        int i = service.add(category);
        if (i==1){
            response.getWriter().write(JSON.toJSONString(Return.success("success")));
        }else {
            response.getWriter().write(JSON.toJSONString(Return.error("error")));
        }
    }

    /**
     * 修改
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //将请求体中数据封装到实体类
        BufferedReader br = request.getReader();
        String s = br.readLine();
        Category category = JSON.parseObject(s, Category.class);
       // System.out.println(category);
        category.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

        Integer root = (Integer) request.getSession().getAttribute("root");
        category.setUpdateUser(root);
        //System.out.println(category);
        int i = service.update(category);
        if (i==1){
            response.getWriter().write(JSON.toJSONString(Return.success("success")));
        }else {
            response.getWriter().write(JSON.toJSONString(Return.error("error")));
        }
    }

    /**
     * 删除
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        int i = service.delete(Long.valueOf((id)));
        if (i==1){
            response.getWriter().write(JSON.toJSONString(Return.success("success")));
        }else {
            response.getWriter().write(JSON.toJSONString(Return.error("error")));
        }
    }

    /**
     * 查询所有
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> list = service.list();
        System.out.println(list);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Return.success(list)));
    }
}



