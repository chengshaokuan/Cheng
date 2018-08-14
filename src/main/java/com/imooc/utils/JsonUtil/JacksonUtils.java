package com.imooc.utils.JsonUtil;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: Cheng
 * @description: jackson工具类
 * @author: Mr.Cheng
 * @create: 2018-08-14 16:50
 **/
public class JacksonUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    private JacksonUtils() {
    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * @Description:  javaBean、列表数组转换为json字符串
     * @param: obj
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 16:57 2018/8/14
     */
    public static String objToJson(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * @Description:  javaBean、列表数组转换为json字符串,忽略空值
     * @param: obj
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 16:57 2018/8/14
     */
    public static String objToJsonIgnoreNull(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(obj);
    }

    /**
     * @Description:  json转JavaBean
     * @param: jsonString
     * @param: clazz
     * @return: T
     * @Author: Mr.Cheng
     * @Date: 16:57 2018/8/14
     */
    public static <T> T jsonToBean(String jsonString, Class<T> clazz) throws Exception {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return objectMapper.readValue(jsonString, clazz);
    }

    /**
     * @Description:  json字符串转换为map
     * @param: jsonString
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: Mr.Cheng
     * @Date: 16:58 2018/8/14
     */
    public static <T> Map<String, Object> jsonToMap(String jsonString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.readValue(jsonString, Map.class);
    }

    /**
     * @Description:  json字符串转换为map
     * @param: jsonString
     * @param: clazz
     * @return: java.util.Map<java.lang.String,T>
     * @Author: Mr.Cheng
     * @Date: 16:58 2018/8/14
     */
    public static <T> Map<String, T> jsonToMap(String jsonString, Class<T> clazz) throws Exception {
        Map<String, Map<String, Object>> map = objectMapper.readValue(jsonString, new TypeReference<Map<String, T>>() {
        });
        Map<String, T> result = new HashMap<String, T>();
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), mapToBean(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * @Description:  深度转换json成map
     * @param: json
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: Mr.Cheng
     * @Date: 16:58 2018/8/14
     */
    public static Map<String, Object> jsonToMapDeeply(String json) throws Exception {
        return jsonToMapRecursion(json, objectMapper);
    }

    /**
     * @Description:  把json解析成list，如果list内部的元素存在jsonString，继续解析
     * @param: json
     * @param: mapper 解析工具
     * @return: java.util.List<java.lang.Object>
     * @Author: Mr.Cheng
     * @Date: 16:58 2018/8/14
     */
    private static List<Object> jsonToListRecursion(String json, ObjectMapper mapper) throws Exception {
        if (json == null) {
            return null;
        }
        List<Object> list = mapper.readValue(json, List.class);
        for (Object obj : list) {
            if (obj != null && obj instanceof String) {
                String str = (String) obj;
                if (str.startsWith("[")) {
                    obj = jsonToListRecursion(str, mapper);
                } else if (obj.toString().startsWith("{")) {
                    obj = jsonToMapRecursion(str, mapper);
                }
            }
        }
        return list;
    }

    /**
     * @Description:  把json解析成map，如果map内部的value存在jsonString，继续解析
     * @param: json
     * @param: mapper
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: Mr.Cheng
     * @Date: 16:59 2018/8/14
     */
    private static Map<String, Object> jsonToMapRecursion(String json, ObjectMapper mapper) throws Exception {
        if (json == null) {
            return null;
        }
        Map<String, Object> map = mapper.readValue(json, Map.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object obj = entry.getValue();
            if (obj != null && obj instanceof String) {
                String str = ((String) obj);
                if (str.startsWith("[")) {
                    List<?> list = jsonToListRecursion(str, mapper);
                    map.put(entry.getKey(), list);
                } else if (str.startsWith("{")) {
                    Map<String, Object> mapRecursion = jsonToMapRecursion(str, mapper);
                    map.put(entry.getKey(), mapRecursion);
                }
            }
        }
        return map;
    }

    /**
     * @Description:  与javaBean json数组字符串转换为列表
     * @param: jsonArrayStr
     * @param: clazz
     * @return: java.util.List<T>
     * @Author: Mr.Cheng
     * @Date: 16:59 2018/8/14
     */
    public static <T> List<T> jsonTolist(String jsonArrayStr, Class<T> clazz) throws Exception {

        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        List<T> lst = (List<T>) objectMapper.readValue(jsonArrayStr, javaType);
        return lst;
    }

    /**
     * @Description:  获取泛型的Collection Type
     * @param: collectionClass 泛型的Collection
     * @param: elementClasses 元素类
     * @return: com.fasterxml.jackson.databind.JavaType Java类型
     * @Author: Mr.Cheng
     * @Date: 16:59 2018/8/14
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * @Description:  map转JavaBean
     * @param: map
     * @param: clazz
     * @return: T
     * @Author: Mr.Cheng
     * @Date: 17:00 2018/8/14
     */
    public static <T> T mapToBean(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    /**
     * @Description:  map转json
     * @param: map
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 17:00 2018/8/14
     */
    public static String mapToJson(Map map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @Description:  map转JavaBean
     * @param: obj
     * @param: clazz
     * @return: T
     * @Author: Mr.Cheng
     * @Date: 17:00 2018/8/14
     */
    public static <T> T objToBean(Object obj, Class<T> clazz) {
        return objectMapper.convertValue(obj, clazz);
    }

}
