package com.zcx.pojo;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 购物车
 */
@Data
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String     name;       //名称

    private Long       userId;     //用户id

    private Integer    goodId;     //菜品id

    private Integer    number;     //数量

    private BigDecimal amount;     //金额

    private String     image;      //图片

    private String     createTime; //创建时间
}
