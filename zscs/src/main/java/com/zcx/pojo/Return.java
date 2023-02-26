package com.zcx.pojo;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class Return<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据


    public static <T> Return<T> success(T object) {
        Return<T> r = new Return<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> Return<T> error(String msg) {
        Return r = new Return();
        r.msg = msg;
        r.code = 0;
        return r;
    }



}
