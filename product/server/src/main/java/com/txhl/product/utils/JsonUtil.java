package com.txhl.product.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;

/**
 * Json格式化
 *
 * @author Administrator
 * @create 2018-04-17 21:19
 */
public class JsonUtil<T> {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static GsonBuilder gsonBuilder = new GsonBuilder();

    public JsonUtil() {
    }

    public static String toJson(Object obj) {
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create().toJson(obj);
    }

    public static<T> T fromJson(String msg,Class<T> type) {
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create().fromJson(msg,type);
    }
}
