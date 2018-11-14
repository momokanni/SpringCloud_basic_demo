package com.txhl.product.entity;

import com.txhl.product.common.enums.ProductStatus;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 *
 * @author Administrator
 * @create 2018-03-29 20:19
 */
@Entity
@DynamicUpdate
@Data
@Table(name = "product_info")
public class Product implements Serializable{

    private static final long serialVersionUID = -956367635283320631L;

    @Id
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

    private Date createTime;

    private Date updateTime;
}
