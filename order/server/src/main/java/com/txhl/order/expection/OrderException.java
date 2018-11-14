package com.txhl.order.expection;

import com.txhl.order.enums.ResultEnums;

/**
 * 订单异常类
 *
 * @author Administrator
 * @create 2018-10-15 15:28
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code,String msg) {
        super(msg);
        this.code = code;
    }

    public OrderException(ResultEnums resultEnums) {
        super(resultEnums.getMsg());
        this.code = resultEnums.getCode();
    }
}
