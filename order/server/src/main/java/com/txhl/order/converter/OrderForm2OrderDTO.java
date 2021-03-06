package com.txhl.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.txhl.order.dto.OrderMasterDTO;
import com.txhl.order.entity.OrderDetail;
import com.txhl.order.enums.ResultEnums;
import com.txhl.order.expection.OrderException;
import com.txhl.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * order表单转换工具类
 *
 * @author Administrator
 * @create 2018-10-15 20:45
 */
@Slf4j
public class OrderForm2OrderDTO {

    public static OrderMasterDTO convert(OrderForm orderForm){

        Gson gson = new Gson();
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();

        orderMasterDTO.setBuyerName(orderForm.getName());
        orderMasterDTO.setBuyerAddress(orderForm.getAddress());
        orderMasterDTO.setBuyerPhone(orderForm.getPhone());
        orderMasterDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e){
            log.error("【对象转换】错误,String={}",orderForm.getItems());
            throw new OrderException(ResultEnums.PARAM_CONVERT_ERROR);
        }
        orderMasterDTO.setOrderDetailList(orderDetailList);
        return orderMasterDTO;

    }
}
