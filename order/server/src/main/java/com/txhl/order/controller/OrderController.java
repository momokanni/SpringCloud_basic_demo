package com.txhl.order.controller;

import com.txhl.order.converter.OrderForm2OrderDTO;
import com.txhl.order.dto.OrderMasterDTO;
import com.txhl.order.enums.ResultEnums;
import com.txhl.order.expection.OrderException;
import com.txhl.order.form.OrderForm;
import com.txhl.order.service.OrderService;
import com.txhl.order.utils.ResultUtils;
import com.txhl.order.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单
 *
 * @author Administrator
 * @create 2018-10-14 1:11
 */
@Slf4j
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/create")
    public ResultVO create(@Valid OrderForm orderForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            log.error("【创建订单】 参数不正确 orderForm={}",orderForm);
            throw new OrderException(ResultEnums.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderMasterDTO orderMasterDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderMasterDTO.getOrderDetailList())){
            log.error("【创建订单】 购物车不能为空");
            throw new OrderException(ResultEnums.SHOPCAR_NOT_EMPTY);
        }

        OrderMasterDTO createResult = orderService.create(orderMasterDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());

        return ResultUtils.success(map);
    }

    /**
     * 订单完结
     * @param orderId
     * @return
     */
    @PostMapping(value = "/finish")
    public ResultVO<OrderMasterDTO> finish(@RequestParam(value = "orderId") String orderId){

        return ResultUtils.success(orderService.finish(orderId));
    }
}
