package com.zcx.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单明细
 */
@Data
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;       //名称

    private Long orderId;      //订单id

    private Integer goodId;    //商品id

    private Integer number;    //数量

    private BigDecimal amount; //金额

    private String image;      //图片
}
