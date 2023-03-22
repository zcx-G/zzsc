package com.zcx.controller;


import com.zcx.pojo.ShoppingCart;
import com.zcx.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController{
     @Autowired
     private ShoppingCartService shoppingCartService;

    /**
     * 添加
     */
    @PostMapping
    public Return add(@RequestBody ShoppingCart shoppingCart , HttpServletRequest request) {
        String time = LocalDateTime.now().toLocalDate() + " " + LocalDateTime.now().toLocalTime();
        shoppingCart.setCreateTime(time);
        Long userId = (Long) request.getSession().getAttribute("user");
        shoppingCart.setUserId(userId);
        ShoppingCart good = shoppingCartService.selectByGoodId(shoppingCart.getGoodId(), shoppingCart.getUserId());

        if (good == null){
            boolean flag = shoppingCartService.add(shoppingCart);
            if (flag){
                return Return.success("success");
            }else {
                return Return.error("error");
            }
        }else {
            boolean flag = shoppingCartService.addNumber(shoppingCart.getGoodId(), shoppingCart.getUserId());
            if (flag){
                return Return.success("success");
            }else {
                return Return.error("error");
            }
        }
    }

    /**
     *减少
     */
    @PutMapping
    public Return sub(@RequestBody ShoppingCart shoppingCart , HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user");

        shoppingCart.setUserId(userId);
        boolean flag = shoppingCartService.subNumber(shoppingCart.getGoodId(), shoppingCart.getUserId());
        ShoppingCart good = shoppingCartService.selectByGoodId(shoppingCart.getGoodId(), shoppingCart.getUserId());
        if (good.getNumber()==0)
            shoppingCartService.deleteOne(shoppingCart.getGoodId(), shoppingCart.getUserId());
        if (flag){
            return Return.success("success");
        }else {
            return Return.error("error");
        }
    }

    /**
     *查询所有
     */
    @GetMapping
    public Return list(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("user");
        List<ShoppingCart> list = shoppingCartService.list(userId);
        return Return.success(list);
    }

    /**
     *清空
     */
    @DeleteMapping
    public Return clean(HttpServletRequest request){
        Long userId = (Long) request.getSession().getAttribute("user");
        boolean flag = shoppingCartService.deleteAll(userId);
        if (flag){
            return Return.success("success");
        }else {
            return Return.error("error");
        }
    }
}



