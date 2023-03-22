package com.zcx.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单
 */
@Data
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String number;  //订单号

    //订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
    private Integer status;

    private Long userId;      //下单用户id

    private Long addressBookId;  //地址id

    private String orderTime;    //下单时间

    private Integer payMethod;   //支付方式 1微信，2支付宝

    private BigDecimal amount;   //实收金额

    private String remark;       //备注

    private String userName;     //用户名

    private String phone;        //手机号

    private String address;      //地址

    private String consignee;     //收货人
}
