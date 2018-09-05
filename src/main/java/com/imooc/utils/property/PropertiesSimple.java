package com.imooc.utils.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @program: Demo
 * @description: 简单工具类
 * @author: Mr.Cheng
 * @create: 2018-07-19 15:03
 **/
public class PropertiesSimple {

    private static Map<String, String> map = new HashMap<String, String>();

    static {
        try {
            InputStream inputStream = PropertiesReader.class.getClassLoader().getResourceAsStream("timeUtil_zh_CN.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            Iterator<Map.Entry<Object, Object>> iterator = properties.entrySet().iterator();
            Map<String, String> tmap = new HashMap<String, String>();
            while (iterator.hasNext()) {
                Map.Entry<Object, Object> objectObjectEntry = iterator.next();
                tmap.put(objectObjectEntry.getKey().toString(), objectObjectEntry.getValue().toString());
            }
            map.clear();
            map.putAll(tmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue (String key) {
        return map.get(key);
    }

    public static void main (String[] args) throws Exception {
        System.out.println(getValue("multi.year"));
    }
}
