package com.zcx.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户
 */

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long    id;

    private String  name;      //姓名

    private String  phone;     //手机号

    private String  sex;       //性别 0 女 1 男

    private String  idNumber;  //身份证号

    private Integer status;    //状态 0:禁用，1:正常
}
