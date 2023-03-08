package com.zcx.pojo;

import lombok.Data;

/**
 * 管理员
 */

@Data
public class Root {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;       //名称

    private String username;   //用户名

    private String password;   //密码

}
