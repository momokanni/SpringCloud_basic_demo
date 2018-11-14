package com.txhl.order.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;

import java.io.IOException;

/**
 * Json格式化
 *
 * @author Administrator
 * @create 2018-04-17 21:19
 */
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static GsonBuilder gsonBuilder = new GsonBuilder();

    public JsonUtil() {
    }

    public static String toJson(Object obj) {
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create().toJson(obj);
    }

    public static Object fromJson(String msg,Class type) {
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create().fromJson(msg,type);
    }

    public static Object fromJson(String msg, TypeReference reference) {
        try {
            return mapper.readValue(msg,reference);
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
