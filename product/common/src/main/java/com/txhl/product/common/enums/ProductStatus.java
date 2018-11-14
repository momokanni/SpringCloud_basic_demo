package com.txhl.product.common.enums;

import lombok.Getter;

/**
 * class_name: ProductStatus
 * package: com.txhl.product.enums
 * describe: 商品状态枚举
 * @author: sl
 * creat_date: 2018/10/12
 * creat_time: 14:54
 **/
@Getter
public enum ProductStatus {
    NORMAL(0,"上架"),SETOFF(1,"下架");

    private Integer code;

    private String msg;

    ProductStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
