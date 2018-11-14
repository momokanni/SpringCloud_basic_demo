package com.txhl.product.expections;

import com.txhl.product.enums.ResultEnums;

/**
 * 产品异常
 *
 * @author Administrator
 * @create 2018-10-16 15:07
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException() {
    }

    public ProductException(Integer code,String msg) {
        super(msg);
        this.code = code;
    }

    public ProductException(ResultEnums resultEnums) {
        super(resultEnums.getMsg());
        this.code = resultEnums.getCode();
    }
}
