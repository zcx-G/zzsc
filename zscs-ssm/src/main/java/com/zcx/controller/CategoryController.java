package com.zcx.controller;


import com.zcx.pojo.Category;
import com.zcx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/categorys")
public class CategoryController{

    @Autowired
    private CategoryService categoryService;

    /**
     * 分页查询
     */
    @GetMapping
    public Return page(@RequestParam Integer page,@RequestParam Integer pageSize){
        return categoryService.selectByPage(page, pageSize);
    }

    /**
     * 添加
     */
    @PostMapping
    public Return add(@RequestBody Category category , HttpServletRequest request){
        category.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

        System.out.println(category);
        Integer root = (Integer) request.getSession().getAttribute("root");
        category.setUpdateUser(root);

        int i = categoryService.add(category);
        if (i==1){
            return Return.success("success");
        }else {
            return Return.error("error");
        }
    }

    /**
     * 修改
     */
    @PutMapping
    public Return update(@RequestBody Category category , HttpServletRequest request){
        category.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        Integer root = (Integer) request.getSession().getAttribute("root");
        category.setUpdateUser(root);
        int i = categoryService.update(category);
        if (i==1){
            return Return.success("success");
        }else {
            return Return.error("error");
        }
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public Return delete(@PathVariable  Long id){
        int i = categoryService.delete(id);
        if (i==1){
            return Return.success("success");
        }else {
            return Return.error("error");
        }
    }

    /**
     * 查询所有
     */
    @GetMapping("/list")
    public Return list(){
        List<Category> list = categoryService.list();
        return Return.success(list);
    }
}



