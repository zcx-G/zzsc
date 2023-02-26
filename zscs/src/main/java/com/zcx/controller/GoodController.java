package com.zcx.controller;


import com.alibaba.fastjson.JSON;
import com.zcx.pojo.Good;
import com.zcx.pojo.PageBean;
import com.zcx.pojo.Return;
import com.zcx.service.GoodService;
import com.zcx.service.impl.GoodServiceImpl;
import com.zcx.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/good/*")
public class GoodController extends BaseServlet {
    private final GoodService service = new GoodServiceImpl();


    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _page = request.getParameter("page");
        String _pageSize = request.getParameter("pageSize");

        int currentPage=Integer.parseInt(_page);
        int pageSize=Integer.parseInt(_pageSize);
        String name = request.getParameter("name");
        if (name!=null && name!="")
            name =new String(name.getBytes("ISO-8859-1"),"utf-8");
        Return<PageBean> categoryReturn= service.selectGoodPage(currentPage, pageSize,name);

        String jsonString = JSON.toJSONString(categoryReturn);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String s = br.readLine();
        int[] ids = JSON.parseObject(s, int[].class);

        int i = service.delete(ids);
        if (i>=1){
            response.getWriter().write(JSON.toJSONString(Return.success("success")));
        }else {
            response.getWriter().write(JSON.toJSONString(Return.error("error")));
        }
    }

    public void status(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String s = br.readLine();
        System.out.println(s);
        int[] ids = JSON.parseObject(s, int[].class);
        String status = request.getParameter("status");
        int i = service.status(ids, Integer.parseInt(status));
        if (i>=1){
            response.getWriter().write(JSON.toJSONString(Return.success("success")));
        }else {
            response.getWriter().write(JSON.toJSONString(Return.error("error")));
        }
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //将请求体中数据封装到实体类
        BufferedReader br = request.getReader();
        String s = br.readLine();
        Good good = JSON.parseObject(s, Good.class);
        good.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        Integer root = (Integer) request.getSession().getAttribute("root");
        good.setUpdateUser(root);
        System.out.println(good);
        int i = service.add(good);
        if (i>=1){
            response.getWriter().write(JSON.toJSONString(Return.success("success")));
        }else {
            response.getWriter().write(JSON.toJSONString(Return.error("error")));
        }
    }
    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //将请求体中数据封装到实体类
        BufferedReader br = request.getReader();
        String s = br.readLine();
        Good good = JSON.parseObject(s, Good.class);
        System.out.println(good);
        good.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        Integer root = (Integer) request.getSession().getAttribute("root");
        good.setUpdateUser(root);
        System.out.println(root);

        System.out.println(good);
        int i = service.update(good);
        if (i==1){
            
            response.getWriter().write(JSON.toJSONString(Return.success("success")));
        }else {
            response.getWriter().write(JSON.toJSONString(Return.error("error")));
        }
    }

    public void selectById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
       // System.out.println(id);
        Good good = service.selectById(Integer.parseInt(id));
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Return.success(good)));

    }
    public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Good> goods = service.list();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Return.success(goods)));

    }

    public void categoryList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String categoryName = new String(request.getParameter("categoryName").getBytes("iso-8859-1"), "utf-8");
        List<Good> goods = service.categoryList(categoryName);
        System.out.println(categoryName);;
        System.out.println(goods);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Return.success(goods)));

    }

}


