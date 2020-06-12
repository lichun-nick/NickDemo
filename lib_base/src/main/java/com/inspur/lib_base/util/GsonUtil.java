package com.inspur.lib_base.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;

/**
 * @author lichun
 */
public class GsonUtil {

    private static Gson gson = new Gson();

    public static <T> T transform(String json,Class<T> clazz){
        return gson.fromJson(json,clazz);
    }

    public static <T> List<T> transformList(String json,Class<T> clazz){
        T[] ts= gson.fromJson(json,TypeToken.getArray(clazz).getType());
        return Arrays.asList(ts);
    }
}
