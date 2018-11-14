package com.txhl.order.utils;

import java.util.Random;

/**
 * 随机生成Key
 *
 * @author Administrator
 * @create 2018-10-14 15:37
 */
public class KeyUtil {

    /**
     * 生成唯一key,避免多线程ID重复
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer randomId = random.nextInt(900000)+100000;

        return System.currentTimeMillis() + String.valueOf(randomId);
    }
}
