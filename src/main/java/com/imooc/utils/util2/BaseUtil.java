package com.imooc.utils.util2.utils;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

import static com.creditease.panda.engine.utils.CipherUtil.md5;
import static com.creditease.panda.engine.utils.NumberFormatUtil.formatDb2Str;

/**
 * Created by Nyankosensei on 2017/7/7.
 */
public class BaseUtil {

    public static String generateSignInfoForPhenix(Object obj, String seed) throws IllegalAccessException {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();

        treeMap = convertObj2TreeMap(obj, treeMap, null);

        String singStr = bulidSignStr(treeMap, seed);
        return md5(singStr);

    }

    public static String bulidSignStr(TreeMap<String, String> map, String seed) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<String, String>> entrySet = map.entrySet();

        for (Map.Entry<String, String> entry : entrySet) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (StringUtils.isNotEmpty(v) && !"null".equalsIgnoreCase(v) && !"signInfo".equals(k) && !"seed".equals(k))
                sb.append(k + "=" + v + "$");
        }
        sb.append(seed);
        return sb.toString();
    }

    public static TreeMap<String, String> convertObj2TreeMap(Object obj, TreeMap<String, String> map, String prefix) throws IllegalAccessException {
        if (obj == null) {
            return map;
        }

        if (obj instanceof Map) {
            Map<?, ?> m = (Map<?, ?>) obj;
            for (Iterator<?> i = m.keySet().iterator(); i.hasNext(); ) {
                String key = String.valueOf(i.hasNext());
                putMap(key, m.get(key), map, prefix);
            }
        } else {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(obj);
                putMap(field.getName(), value, map, prefix);
            }
        }

        map.remove("__equalsCalc");
        map.remove("__hashCodeCalc");
        map.remove("serialVersionUID");
        map.remove("signInfo");// 本身的签名信息不参与签名
        map.remove("typeDesc");// 本身的签名信息不参与签名

        return map;
    }

    private static void putMap(String name, Object value, TreeMap<String, String> map, String prefix) throws IllegalAccessException {
        if (value == null)
            return;


        String key = (prefix == null ? name : prefix + "_" + name);
        if (key.contains("_serialVersionUI"))
            return;

        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
            map.put(key, value.toString());
            return;
        }


        if (value instanceof Double) {
            map.put(key, formatDb2Str((Double) value));
            return;
        }

        if (value instanceof List) {
            List<?> list = (List<?>) value;
            for (int i = 0; i < list.size(); i++) {
                Object currObj = list.get(i);
                if ((currObj instanceof String) || (currObj instanceof Integer) || (currObj instanceof Long)) {
                    key = prefix == null ? i + "_" + name : prefix + "_" + i + "_" + name;
                    map.put(key, value.toString());
                } else if (currObj instanceof Double) {
                    key = prefix == null ? i + "_" + name : prefix + "_" + i + "_" + name;
                    map.put(key, formatDb2Str((Double) currObj));
                } else {
                    convertObj2TreeMap(currObj, map, String.valueOf(i));
                }
            }
            return;
        }

        if (value instanceof Object[]) {
            Object[] list = (Object[]) value;
            for (int i = 0; i < list.length; i++) {
                Object currObj = list[i];
                if ((currObj instanceof String) || (currObj instanceof Integer) || (currObj instanceof Long)) {
                    key = prefix == null ? i + "_" + name : prefix + "_" + i + "_" + name;
                    map.put(key, value.toString());
                } else if (currObj instanceof Double) {
                    key = prefix == null ? i + "_" + name : prefix + "_" + i + "_" + name;
                    map.put(key, formatDb2Str((Double) currObj));
                } else {
                    convertObj2TreeMap(currObj, map, String.valueOf(i));
                }
            }
        }

    }

}
