package com.zcx.pojo;


import lombok.Data;

import java.io.Serializable;

/**
 * 地址簿
 */
@Data
public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //用户id
    private Long userId;

    //收货人
    private String consignee;

    //手机号
    private String phone;

    //性别 0 女 1 男
    private String sex;

    //详细地址
    private String detail;

    //标签
    private String label;

    //是否默认 0 否 1是
    private Integer isDefault;

   // 修改时间
    private String updateTime;

    //修改人
    private Long updateUser;

}
