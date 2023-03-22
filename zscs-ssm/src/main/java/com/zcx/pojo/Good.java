package com.zcx.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 商品
 */
@Data
public class Good implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String       name;           //商品名称

    private String       categoryName;   //商品分类名称

    private Long         categoryId;     //商品分类ID

    private BigDecimal   price;          //商品价格

    private  Integer     saleNum;        //销量

    private String       image;          //图片

    private String       description;    //描述信息

    private Integer      status;         //0 停售 1 起售

    private Integer      sort;           //顺序

    private String       updateTime;     //修改时间

    private Integer      updateUser;     //修改人

}
