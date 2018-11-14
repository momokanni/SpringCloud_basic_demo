package com.txhl.order.utils;

import com.txhl.order.enums.CodeEnums;

/**
 * 枚举工具类
 *
 * @author Administrator
 * @create 2018-10-15 20:30
 */
public class EnumUtil {

    public static<T extends CodeEnums> T getByCode(Integer code,Class<T> enumsCLass){
        for (T each : enumsCLass.getEnumConstants()){
            if (each.getCode().equals(code)){
                return each;
            }
        }
        return null;
    }
}
