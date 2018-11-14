package com.txhl.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据组装类
 *
 * @author Administrator
 * @create 2018-10-12 15:48
 */
@Data
public class ResultVO<T> implements Serializable {


    private static final long serialVersionUID = 7341219806800243324L;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String msg;

    private T data;
}
