package com.zcx.pojo;


import lombok.Data;

import java.io.Serializable;

/**
 * 分类
 */
@Data
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String    name;        //分类名称

    private Integer   sort;        //顺序

    private String    updateTime;  //更新时间

    private Integer   updateUser;  //修改人

}
