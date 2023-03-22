package com.zcx.controller;

import lombok.Data;

/**
 * 返回前端格式
 * @param
 */
@Data
public class Return {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private Object data; //数据


    public static  Return success(Object object) {
        Return r = new Return();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static Return error(String msg) {
        Return r = new Return();
        r.msg = msg;
        r.code = 0;
        return r;
    }


//
//
//    private Integer code; //编码：1成功，0和其它数字为失败
//
//    private String msg; //错误信息
//
//    private T data; //数据
//
//
//    public static <T> Return<T> success(T object) {
//        Return<T> r = new Return<T>();
//        r.data = object;
//        r.code = 1;
//        return r;
//    }
//
//    public static <T> Return<T> error(String msg) {
//        Return r = new Return();
//        r.msg = msg;
//        r.code = 0;
//        return r;
//    }

}
