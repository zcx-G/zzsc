package com.zcx.controller;


import com.alibaba.fastjson.JSON;
import com.zcx.pojo.AddressBook;
import com.zcx.pojo.Return;
import com.zcx.service.AddressBookService;
import com.zcx.service.impl.AddressBookServiceImpl;
import com.zcx.web.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/address/*")
public class  AddressBookController extends BaseServlet {
     AddressBookService service = new AddressBookServiceImpl();


    /**
     * 添加地址
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("sava");
        //将请求体中数据封装到实体类
        BufferedReader br = request.getReader();
        String s = br.readLine();
        AddressBook addressBook = JSON.parseObject(s, AddressBook.class);

        String time = LocalDateTime.now().toLocalDate() + " " + LocalDateTime.now().toLocalTime();
        Long userId = (Long) request.getSession().getAttribute("user");

        addressBook.setUpdateTime(time);
        addressBook.setUserId(userId);

        int i = service.add(addressBook);
        if (i==1){
            response.getWriter().write(JSON.toJSONString(Return.success("success")));
        }else {
            response.getWriter().write(JSON.toJSONString(Return.error("error")));
        }
    }

    /**
     * 修改地址
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedReader br = request.getReader();
        String s = br.readLine();
        AddressBook addressBook = JSON.parseObject(s, AddressBook.class);

        String time = LocalDateTime.now().toLocalDate() + " " + LocalDateTime.now().toLocalTime();
        Long userId = (Long) request.getSession().getAttribute("user");

        addressBook.setUpdateTime(time);
        addressBook.setUserId(userId);

        System.out.println(addressBook);
        int i = service.update(addressBook);
        if (i==1){
            response.getWriter().write(JSON.toJSONString(Return.success("success")));
        }else {
            response.getWriter().write(JSON.toJSONString(Return.error("error")));
        }
    }


    /**
     * 删除地址
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json;charset=utf-8");
        String id = request.getParameter("id");
        System.out.println("删除"+id);

        int i = service.delete(Long.valueOf(id));
        if (i==1){
            response.getWriter().write(JSON.toJSONString(Return.success("success")));
        }else {
            response.getWriter().write(JSON.toJSONString(Return.error("error")));
        }

    }

    /**
     * //根据Id查找地址
     */
    public void selectById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        System.out.println("查询"+id);
        AddressBook addressBook = service.selectById(Long.valueOf(id));

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Return.success(addressBook)));
    }


    /**
     * 将地址状态修改
     */
    public void Default (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        Long userId = (Long) request.getSession().getAttribute("user");
        String id = request.getParameter("id");
        if (id==null){
            AddressBook addressBook = service.selectDefaultAddress(userId);
            if(addressBook == null){
                response.getWriter().write(JSON.toJSONString(Return.error("没有默认地址")));
            }else {
                response.getWriter().write(JSON.toJSONString(Return.success(addressBook)));
            }
        }else {
            service.updateDefault(userId);
            int i = service.updateDefaultById(Long.valueOf(id));
            if (i==1){
                response.getWriter().write(JSON.toJSONString(Return.success("success")));
            }else {
                response.getWriter().write(JSON.toJSONString(Return.error("error")));
            }
        }
    }

    /**
     * 查询所有地址
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = (Long) request.getSession().getAttribute("user");
        List<AddressBook> list = service.selectAddressList(userId);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Return.success(list)));

    }
}



