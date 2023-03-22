package com.zcx.controller;



import com.zcx.pojo.User;
import com.zcx.service.UserService;
import com.zcx.utils.CheckCodeUtil;
import com.zcx.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

    /**
     * 验证码
     */
    @GetMapping("/{phone}")
    public Return msg(@PathVariable String phone , HttpServletRequest request){
        System.out.println("移动端验证码");
        String code = CheckCodeUtil.generateVerifyCode(6,"0123456789");
        System.out.println(code);
        SMSUtils.sendMessage(phone,code);
        request.getSession().setAttribute("code", code);
        return Return.success("验证码已发送，请注意查收");
    }

    /**
     * 登录
     */
    @GetMapping
    public Return login(@RequestParam String phone, @RequestParam String code , HttpServletRequest request) {
        System.out.println("移动端登录");
        System.out.println(phone);
        System.out.println(code);
        Object sessionCode = request.getSession().getAttribute("code");
        if (sessionCode!=null && sessionCode.equals(code)) {
            User user1 = userService.userSelect(phone);
            if (user1 == null){
                boolean b = userService.add(phone);
            }
            user1 = userService.userSelect(phone);
            System.out.println(user1);
            request.getSession().setAttribute("user",user1.getId());
            return Return.success(user1);

        }else
            return Return.error("手机号或验证码错误");
    }

    /**
     * 登出
     */
    @PutMapping
    public Return logout(HttpServletRequest request){
        System.out.println("移动端退出登录");
        //清理Session中当前登陆的用户id
        request.getSession().removeAttribute("user");
        return Return.success("退出成功");

    }
}


