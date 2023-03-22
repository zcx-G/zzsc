package com.zcx.pojo;


import lombok.Data;

import java.io.Serializable;

/**
 * 地址簿
 */
@Data
public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long    id;

    private Long    userId;       //用户id

    private String  consignee;    //收货人

    private String  phone;        //手机号

    private String  sex;          //性别 0 女 1 男

    private String  detail;       //详细地址

    private String  label;        //标签

    private Integer isDefault;    //是否默认 0 否 1是

    private String  updateTime;   // 修改时间


}
