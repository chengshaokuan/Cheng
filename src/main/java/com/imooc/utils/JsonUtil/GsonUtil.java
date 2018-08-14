package com.imooc.utils.JsonUtil;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: Cheng
 * @description: Gson工具类解析
 * @author: Mr.Cheng
 * @create: 2018-07-20 10:32
 **/
public class GsonUtil {

    private static Gson gson = null;

    static {
        if (gson == null) {
            //gson = new Gson();
            //当使用GsonBuilder方式时属性为空的时候输出来的json字符串是有键值key的,显示形式是"key":null，而直接new出来的就没有"key":null的
            gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }
    }

    private GsonUtil () {
    }

    /**
     * @Description: 将object对象转成json字符串
     * @param: object
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 16:09 2018/8/14
     */
    public static String gsonString (Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * @Description: 将json转成特定的cls的对象
     * @param: gsonString
     * @param: cls
     * @return: T
     * @Author: Mr.Cheng
     * @Date: 16:08 2018/8/14
     */
    public static <T> T gsonToBean (String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * @Description: json字符串转成list
     * 泛型在编译期类型被擦除导致报错
     * @param: gsonString
     * @param: cls
     * @return: java.util.List<T>
     * @Author: Mr.Cheng
     * @Date: 16:08 2018/8/14
     */
    public static <T> List<T> gsonToList (String gsonString, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            //根据泛型返回解析指定的类型,TypeToken<List<T>>{}.getType()获取返回类型
            list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * @Description: 转成list
     * 解决泛型问题
     * @param: json
     * @param: cls
     * @return: java.util.List<T>
     * @Author: Mr.Cheng
     * @Date: 16:07 2018/8/14
     */
    public static <T> List<T> jsonToList (String json, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (final JsonElement elem : array) {
                list.add(gson.fromJson(elem, cls));
            }
        }
        return list;
    }

    /**
     * @Description: json字符串转成list中有map的
     * @param: gsonString
     * @return: java.util.List<Map                               <                               String                               ,                               T>>
     * @Author: Mr.Cheng
     * @Date: 16:06 2018/8/14
     */
    public static <T> List<Map<String, T>> gsonToListMaps (String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * @Description: json字符串转成map的
     * @param: gsonString
     * @return: Map<String   , T>
     * @Author: Mr.Cheng
     * @Date: 16:05 2018/8/14
     */
    public static <T> Map<String, T> gsonToMaps (String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }


}

