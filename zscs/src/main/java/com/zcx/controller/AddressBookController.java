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


    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("sava");
        //将请求体中数据封装到实体类
        BufferedReader br = request.getReader();
        String s = br.readLine();
        AddressBook addressBook = JSON.parseObject(s, AddressBook.class);

        String time = LocalDateTime.now().toLocalDate() + " " + LocalDateTime.now().toLocalTime();
        addressBook.setUpdateTime(time);

        Long userId = (Long) request.getSession().getAttribute("user");
        System.out.println(userId);
        addressBook.setUpdateUser(userId);
        addressBook.setUserId(userId);

        System.out.println(addressBook);
        int i = service.add(addressBook);
        if (i==1){
            response.getWriter().write(JSON.toJSONString(Return.success("success")));
        }else {
            response.getWriter().write(JSON.toJSONString(Return.error("error")));
        }
    }




    public void Default (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long userId = (Long) request.getSession().getAttribute("user");
        String id = request.getParameter("id");
        if (id==null){
            AddressBook addressBook = service.selectDefaultAddress(userId);
            System.out.println("数据"+addressBook);
            response.setContentType("text/json;charset=utf-8");
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

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long userId = (Long) request.getSession().getAttribute("user");
        List<AddressBook> list = service.selectAddressList(userId);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Return.success(list)));

    }
}



