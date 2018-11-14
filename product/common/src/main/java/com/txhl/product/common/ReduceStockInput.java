package com.txhl.product.common;

import lombok.Data;

/**
 * 购物车指定输入类
 *
 * @author Administrator
 * @create 2018-10-16 23:19
 */

@Data
public class ReduceStockInput {

    //商品ID
    private String productId;
    //购买数量
    private Integer productQuantity;

    public ReduceStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
