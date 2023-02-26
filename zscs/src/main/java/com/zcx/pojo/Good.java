package com.zcx.pojo;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 商品
 */
@Data
public class Good implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    //商品名称
    private String name;

    //商品分类
    private String categoryName;

    //商品价格
    private BigDecimal price;

    //图片
    private String image;

    //描述信息
    private String description;

    //0 停售 1 起售
    private Integer status;

    //顺序
    private Integer sort;

    //修改时间
    private String updateTime;

    //修改人
    private Integer updateUser;

}
