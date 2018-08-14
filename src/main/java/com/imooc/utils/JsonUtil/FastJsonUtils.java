package com.imooc.utils.JsonUtil;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @program: Cheng
 * @description: fastjson工具类
 * @author: Mr.Cheng
 * @create: 2018-08-14 16:30
 **/
public class FastJsonUtils {

    private static final SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };

    /**
     * @Description:
     * @param: object
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 16:44 2018/8/14
     */
    public static String toJSONString (Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * @Description:
     * @param: object
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 16:44 2018/8/14
     */
    public static String toJSONNoFeatures (Object object) {
        return JSON.toJSONString(object, config);
    }


    /**
     * @Description:
     * @param: text
     * @return: java.lang.Object
     * @Author: Mr.Cheng
     * @Date: 16:44 2018/8/14
     */
    public static Object toBean (String text) {
        return JSON.parse(text);
    }

    /**
     * @Description:
     * @param: text
     * @param: clazz
     * @return: T
     * @Author: Mr.Cheng
     * @Date: 16:43 2018/8/14
     */
    public static <T> T toBean (String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**
     * @Description:  转换为数组
     * @param: text
     * @return: java.lang.Object[]
     * @Author: Mr.Cheng
     * @Date: 16:43 2018/8/14
     */
    public static <T> Object[] toArray (String text) {
        return toArray(text, null);
    }

    /**
     * @Description:  转换为数组
     * @param: text
     * @param: clazz
     * @return: java.lang.Object[]
     * @Author: Mr.Cheng
     * @Date: 16:43 2018/8/14
     */
    public static <T> Object[] toArray (String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    /**
     * @Description:  转换为List
     * @param: text
     * @param: clazz
     * @return: java.util.List<T>
     * @Author: Mr.Cheng
     * @Date: 16:43 2018/8/14
     */
    public static <T> List<T> toList (String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /**
     * @Description:  将string转化为序列化的json字符串
     * @param: text
     * @return: java.lang.Object
     * @Author: Mr.Cheng
     * @Date: 16:41 2018/8/14
     */
    public static Object textToJson (String text) {
        Object objectJson = JSON.parse(text);
        return objectJson;
    }

    /**
     * @Description:  json字符串转化为map
     * @param: s
     * @return: java.util.Map
     * @Author: Mr.Cheng
     * @Date: 16:42 2018/8/14
     */
    public static Map stringToCollect (String s) {
        Map m = JSONObject.parseObject(s);
        return m;
    }

    /**
     * @Description:  将map转化为string
     * @param: m
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 16:42 2018/8/14
     */
    public static String collectToString (Map m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }
}
