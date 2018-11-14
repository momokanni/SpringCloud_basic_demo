package com.txhl.order.service;

import com.txhl.order.dto.OrderMasterDTO;

public interface OrderService {

    /**
     * 创建订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO create(OrderMasterDTO orderMasterDTO);

    /**
     * 订单完结（只能卖家操作）
     * @param orderId
     * @return
     */
    OrderMasterDTO finish(String orderId);
}