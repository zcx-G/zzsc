package com.zcx.controller;


import com.zcx.pojo.AddressBook;
import com.zcx.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/address")
public class  AddressBookController{
     @Autowired
     private AddressBookService addressBookService;

    /**
     * 添加地址
     */
    @PostMapping
    public Return add(@RequestBody AddressBook addressBook , HttpServletRequest request){
        String time = LocalDateTime.now().toLocalDate() + " " + LocalDateTime.now().toLocalTime();
        Long userId = (Long) request.getSession().getAttribute("user");
        addressBook.setUserId(userId);
        addressBook.setUpdateTime(time);

        boolean flag = addressBookService.add(addressBook);
        if (flag){
            return Return.success("success");
        }else {
            return Return.error("error");
        }
    }

    /**
     * 修改地址
     */
    @PutMapping
    public Return update(@RequestBody AddressBook addressBook , HttpServletRequest request) {
        String time = LocalDateTime.now().toLocalDate() + " " + LocalDateTime.now().toLocalTime();
        Long userId = (Long) request.getSession().getAttribute("user");
        addressBook.setUserId(userId);
        addressBook.setUpdateTime(time);
        boolean flag = addressBookService.update(addressBook);
        if (flag){
            return Return.success("success");
        }else {
            return Return.error("error");
        }
    }


    /**
     * 删除地址
     */
    @DeleteMapping("/{id}")
    public Return delete(@PathVariable Long id){
        boolean flag = addressBookService.delete(id);
        if (flag){
            return Return.success("success");
        }else {
            return Return.error("error");
        }

    }

    /**
     * //根据Id查找地址
     */
    @GetMapping("/{id}")
    public Return selectById(@PathVariable Long id){
        AddressBook addressBook = addressBookService.selectById(id);
        return Return.success(addressBook);
    }

    /**
     * 将地址状态修改
     */
    @PutMapping("/{id}")
    public Return Default (@PathVariable Long id , HttpServletRequest request){
        Long userId = (Long) request.getSession().getAttribute("user");
        addressBookService.updateDefault(userId);
        boolean flag = addressBookService.updateDefaultById(id);
        if (flag){
            return Return.success("success");
        }else {
            return Return.error("error");
        }
    }


    @GetMapping("/Default")
    public Return getDefault (HttpServletRequest request){
        Long userId = (Long) request.getSession().getAttribute("user");
        AddressBook addressBook = addressBookService.selectDefaultAddress(userId);
        if(addressBook == null){
            return Return.error("没有默认地址");
        }else {
            return Return.success(addressBook);
        }
    }

    /**
     * 查询所有地址
     */
    @GetMapping
    public Return list(HttpServletRequest request){
        Long userId = (Long) request.getSession().getAttribute("user");
        List<AddressBook> list = addressBookService.selectAddressList(userId);
        return Return.success(list);
    }
}



