package com.txhl.order.utils;

import com.txhl.order.enums.ResultEnums;
import com.txhl.order.vo.ResultVO;

/**
 * 响应结果组装类
 * @author Administrator
 * @create 2018-04-01 21:41
 */
public class ResultUtils {

    public ResultUtils() {
    }

    public static ResultVO success(Object obj){
        ResultVO result = new ResultVO();
        result.setCode(ResultEnums.SUCCESS.getCode());
        result.setMsg(ResultEnums.SUCCESS.getMsg());
        result.setData(obj);
        return result;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
