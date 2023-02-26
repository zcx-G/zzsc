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

    //名称
    private String name;

    //用户id
    private Long userId;

    //菜品id
    private Long goodId;

    //数量
    private Integer number;

    //金额
    private BigDecimal amount;

    //图片
    private String image;

    //创建时间
    private String createTime;
}
