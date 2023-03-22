package com.zcx.controller;


import com.zcx.pojo.Good;
import com.zcx.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodController {
    @Autowired
    private GoodService goodService;

    /**
     * 分页条件查询
     */
    @GetMapping("/page")
    public Return page(@RequestParam Integer page, @RequestParam Integer pageSize , @RequestParam String name) throws UnsupportedEncodingException {
        if (name!=null && name!="")
            name =new String(name.getBytes("ISO-8859-1"),"utf-8");

        return goodService.selectGoodPage(page, pageSize,name);
    }

    /**
     * 删除
     */
    @DeleteMapping
    public Return delete(@RequestBody int[] ids){
        boolean flag = goodService.delete(ids);
        if (flag){
            return Return.success("success");
        }else {
            return Return.error("error");
        }
    }


    /**
     * 状态修改
     */
    @PutMapping("/{status}")
    public Return status(@RequestBody int[] ids, @PathVariable Integer status ){
        boolean flag = goodService.status(ids, status);
        if (flag){
            return Return.success("success");
        }else {
            return Return.error("error");
        }
    }


    /**
     * 添加
     */
    @PostMapping
    public Return add(@RequestBody Good good , HttpServletRequest request){
        good.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        Integer root = (Integer) request.getSession().getAttribute("root");
        good.setUpdateUser(root);
        System.out.println(good);
        boolean flag = goodService.add(good);
        if (flag){
            return Return.success("success");
        }else {
            return Return.error("error");
        }
    }

    /**
     * 修改
     */
    @PutMapping
    public Return update(@RequestBody Good good , HttpServletRequest request){
        good.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        Integer root = (Integer) request.getSession().getAttribute("root");
        good.setUpdateUser(root);
        System.out.println(root);

        System.out.println(good);
        boolean flag = goodService.update(good);
        if (flag){
            return Return.success("success");
        }else {
            return Return.error("error");
        }
    }


    /**
     *根据ID查询
     */
    @GetMapping("/{id}")
    public Return selectById(@PathVariable Integer id){
        Good good = goodService.selectById(id);
        return Return.success(good);

    }

    /**
     *查询所有
     */
    @GetMapping
    public Return list(){
        List<Good> goods = goodService.list();
        return Return.success(goods);
    }

    /**
     *查询当前分类下的商品
     */
    @GetMapping("/categoryList")
    public Return categoryList(@RequestParam Long id) {
        List<Good> goods = goodService.categoryList(id);
        return Return.success(goods);

    }

}


