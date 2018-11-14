package com.txhl.order.service.impl;

import com.txhl.order.dao.OrderDao;
import com.txhl.order.dao.OrderDetailDao;
import com.txhl.order.dto.OrderMasterDTO;
import com.txhl.order.entity.OrderDetail;
import com.txhl.order.entity.OrderMaster;
import com.txhl.order.enums.OrderStatusEnums;
import com.txhl.order.enums.PayStatusEnums;
import com.txhl.order.enums.ResultEnums;
import com.txhl.order.expection.OrderException;
import com.txhl.order.service.OrderService;
import com.txhl.order.utils.KeyUtil;
import com.txhl.product.client.ProductClient;
import com.txhl.product.common.ProductOutput;
import com.txhl.product.common.ReduceStockInput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 订单业务层
 *
 * @author Administrator
 * @create 2018-10-15 21:11
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderDao orderDao;


    /**
     * 创建订单
     * @param orderMasterDTO
     * @return
     */
    @Override
    @Transactional
    public OrderMasterDTO create(OrderMasterDTO orderMasterDTO) {
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        // 查询商品（数量、单价）
        List<String> productIds = orderMasterDTO.getOrderDetailList().stream()
                                  .map(OrderDetail::getProductId)
                                  .collect(Collectors.toList());
        List<ProductOutput> productList = productClient.listForOrder(productIds);

        for (OrderDetail orderDetail : orderMasterDTO.getOrderDetailList()) {
          // 单价 * 数量
            for (ProductOutput product : productList) {
                if(product.getProductId().equals(orderDetail.getProductId())){
                    // 计算总价
                    orderAmount = product.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    //写入订单详情库
                    BeanUtils.copyProperties(product,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.getUniqueKey());
                    orderDetailDao.save(orderDetail);
                }
            }
        }

        //写入订单主库
        OrderMaster orderMaster = new OrderMaster();
        orderMasterDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderMasterDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnums.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnums.WAIT.getCode());
        orderDao.save(orderMaster);
        // 减库存
        List<ReduceStockInput> carDTOList = orderMasterDTO.getOrderDetailList().stream()
                                  .map(e -> new ReduceStockInput(e.getProductId(),e.getProductQuantity()))
                                  .collect(Collectors.toList());
        productClient.reduceStock(carDTOList);
        return orderMasterDTO;
    }

    /**
     * 订单完结(只能卖家操作)
     * @param orderId
     * @return
     */
    @Override
    @Transactional
    public OrderMasterDTO finish(String orderId) {
        // 1、先查询订单
        Optional<OrderMaster> optionalOrderMaster = orderDao.findById(orderId);
        if (!optionalOrderMaster.isPresent()){
            throw new OrderException(ResultEnums.PRODUCT_NOT_EXIST.getCode(),ResultEnums.PRODUCT_NOT_EXIST.getMsg());
        }
        // 2.判断订单状态
        OrderMaster orderMaster = optionalOrderMaster.get();
        if (OrderStatusEnums.NEW.getCode() != orderMaster.getOrderStatus()){
            throw new OrderException(ResultEnums.ORDER_STATUS_ERROR.getCode(),ResultEnums.ORDER_STATUS_ERROR.getMsg());
        }
        // 3.完结订单
        orderMaster.setOrderStatus(OrderStatusEnums.FINISHED.getCode());
        orderDao.save(orderMaster);

        //查询订单详情
        List<OrderDetail> orderDetails = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetails)){
            throw new OrderException(ResultEnums.ORDER_DETAIL_EMPTY.getCode(),ResultEnums.ORDER_DETAIL_EMPTY.getMsg());
        }
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        BeanUtils.copyProperties(orderMaster,orderMasterDTO);
        orderMasterDTO.setOrderDetailList(orderDetails);
        return orderMasterDTO;
    }
}
