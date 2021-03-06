package com.txhl.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.txhl.order.entity.OrderDetail;
import com.txhl.order.enums.OrderStatusEnums;
import com.txhl.order.enums.PayStatusEnums;
import com.txhl.order.utils.Date2LongSerializer;
import com.txhl.order.utils.EnumUtil;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 数据传输
 *
 * @author Administrator
 * @create 2018-04-02 12:05
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderMasterDTO {

    private String orderId;
    //买家名称
    private String buyerName;
    //买家手机号
    private String buyerPhone;
    //配送地址
    private String buyerAddress;
    //买家openid
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount;
    //订单状态
    private Integer orderStatus;
    //支付状态
    private Integer payStatus;
    //创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    //更新时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnums getOrderStatusEnums(){

        return EnumUtil.getByCode(orderStatus,OrderStatusEnums.class);
    }

    @JsonIgnore
    public PayStatusEnums getPayStatusEnums(){

        return EnumUtil.getByCode(payStatus,PayStatusEnums.class);
    }
}
