package com.txhl.product.common;

import com.txhl.product.common.enums.ProductStatus;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 对外暴露类
 *
 * @author Administrator
 * @create 2018-10-16 23:13
 */
@Data
public class ProductOutput {

    private String productId;
    // 名称
    private String productName;
    // 价格
    private BigDecimal productPrice;
    // 库存
    private Integer productStock;
    // 描述
    private String productDescription;
    // 小图
    private String productIcon;
    // 状态
    private Integer productStatus = ProductStatus.SETOFF.getCode();
    // 类目编号
    private Integer categoryType;
}
