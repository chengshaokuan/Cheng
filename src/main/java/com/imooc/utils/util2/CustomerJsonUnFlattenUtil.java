package com.imooc.utils.util2.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.creditease.panda.engine.utils.JsonPathUtil.*;


/**
 * Created by xiaguangmeng on 17/6/30.
 */
public class CustomerJsonUnFlattenUtil {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerJsonUnFlattenUtil.class);

    public static JSONObject GenerateCustomerJson(Map<String, String> map) {

        JSONObject responseJson = new JSONObject();
        Map<JsonPathUtil.JsonPathKey, Map<String, String>> jsonPathKeyMap = new HashMap<JsonPathUtil.JsonPathKey, Map<String, String>>();

        //模块分类
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String headStr = headFirst(key);
            headStr = headStr.replaceAll(".*", BLANK);
            //非标记信息
            if (!key.equals(TAG_INDEX)) {
                JsonPathUtil.JsonPathKey jsonPathKey = key.contains(UNDERLINE) ? JsonPathUtil.JsonPathKey.valuesOf(headStr) : JsonPathUtil.JsonPathKey.OTHERS;
                if (null == jsonPathKeyMap.get(jsonPathKey)) {
                    jsonPathKeyMap.put(jsonPathKey, new HashMap<String, String>());
                }
                jsonPathKeyMap.get(jsonPathKey).put(key.contains(UNDERLINE) ? removePathTag(key, jsonPathKey.getContext()) : key, value);

            } else if (key.equals(TAG_INDEX) && value != null) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    JSONObject tagJson = mapper.readValue(value, JSONObject.class);
                    Iterator<String> iterator = tagJson.keys();
                    while (iterator.hasNext()) {
                        String tag = iterator.next();
                        Object tagObj = tagJson.get(tag);
                        if (null != tagObj) {
                            JSONObject js = mapper.readValue(tagObj.toString(), JSONObject.class);
                            String tagHeadStr = headFirst(tag);
                            tagHeadStr = tagHeadStr.replaceAll(".*", BLANK);
                            JsonPathUtil.JsonPathKey tagPathKey = JsonPathUtil.JsonPathKey.valuesOf(tagHeadStr);
                            Iterator<String> jsIterator = js.keys();
                            while (jsIterator.hasNext()) {
                                String k = jsIterator.next();
                                if (null == jsonPathKeyMap.get(tagPathKey)) {
                                    jsonPathKeyMap.put(tagPathKey, new HashMap<String, String>());
                                }
                                jsonPathKeyMap.get(tagPathKey).put(removePathTag(k, tagPathKey.getContext()), js.getString(k));
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    LOG.error("[customer]-[unflatten]-[jsonmapper]-[exception]:", e.getMessage());
                }
            }
        }

        //unflatten
        for (Map.Entry<JsonPathUtil.JsonPathKey, Map<String, String>> entry : jsonPathKeyMap.entrySet()) {
            if (entry.getValue().size() > 0) {
                switch (entry.getKey().getLayer()) {
                    case ZERO_NUM:
                        if (entry.getKey().compareTo(JsonPathUtil.JsonPathKey.OTHERS) == 0) {
                            responseJson.putAll(entry.getValue());
                        } else {
                            responseJson.put(entry.getKey().getContext(), GenerateUnidimensional(entry.getValue()));
                        }
                        break;
                    case ONE_NUM:
                        if (entry.getKey().compareTo(JsonPathUtil.JsonPathKey.ADDRESS) == 0 || entry.getKey().compareTo(JsonPathUtil.JsonPathKey.CONTACT_WAYS) == 0) {
                            responseJson.put(entry.getKey().getContext(), GenerateListJson(entry.getValue(), CHARS_TWICE_UNDERLINE_PATTERN, UNDERLINE_CHARS_PATTERN));
                        } else {
                            responseJson.put(entry.getKey().getContext(), GenerateListJson(entry.getValue(), CHARS_UNDERLINE_PATTERN, UNDERLIN_RANDOMCHAR_PATTERN));
                        }
                        break;
                    case TWO_NUM:
                        responseJson.put(entry.getKey().getContext(), Generate2ListJson(entry.getValue(), CHARS_UNDERLINE_PATTERN, UNDERLIN_RANDOMCHAR_PATTERN));
                        break;
                    default:
                        LOG.error("GenerateCustomerJson generate json error!");
                        break;
                }
            }
        }
        return responseJson;
    }

    //一维
    public static JSONObject GenerateUnidimensional(Map<String, String> map) {
        JSONObject responseJson = new JSONObject();
        responseJson.putAll(map);
        return responseJson;
    }


    //一层list
    public static JSONArray GenerateListJson(Map<String, String> map, String leftPattern, String rightPattern) {
        JSONArray responseJson = new JSONArray();

        Map<String, Map<String, String>> listMap = map2JsonList(map, leftPattern, rightPattern);
        for (Map.Entry<String, Map<String, String>> entry : listMap.entrySet()) {
            responseJson.add(entry.getValue());
        }
        return responseJson;
    }

    //两层list
    public static JSONArray Generate2ListJson(Map<String, String> map, String leftPattern, String rightPattern) {
        JSONArray responseJson = new JSONArray();

        Map<String, Map<String, String>> listMap = map2JsonList(map, leftPattern, rightPattern);
        for (Map.Entry<String, Map<String, String>> entry : listMap.entrySet()) {
            responseJson.add(GenerateObj(entry.getValue()));
        }
        return responseJson;
    }

    private static JSONObject GenerateObj(Map<String, String> map) {

        JSONObject responseJson = new JSONObject();

        Map<String, String> contactMap = new HashMap<String, String>();
        Map<String, String> addressMap = new HashMap<String, String>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            String headStr = headFirst(key);
            headStr = headStr.replaceAll(".*", BLANK);
            if (headStr.equals(JsonPathUtil.JsonPathKey.ADDRESS.getContext())) {
                String field = replacePattern(key, CHARS_UNDERLINE_PATTERN);
                addressMap.put(field, value);
            } else if (headStr.equals(JsonPathUtil.JsonPathKey.CONTACT_WAYS.getContext())) {
                String field = replacePattern(key, CHARS_UNDERLINE_PATTERN);
                contactMap.put(field, value);
            } else {
                responseJson.put(key, value);
            }
        }

        if (addressMap.size() > 0) {
            responseJson.put(JsonPathUtil.JsonPathKey.ADDRESS.getContext(), GenerateListJson(addressMap, CHARS_TWICE_UNDERLINE_PATTERN, UNDERLINE_CHARS_PATTERN));
        }
        if (contactMap.size() > 0) {
            responseJson.put(JsonPathUtil.JsonPathKey.CONTACT_WAYS.getContext(), GenerateListJson(contactMap, CHARS_TWICE_UNDERLINE_PATTERN, UNDERLINE_CHARS_PATTERN));
        }

        return responseJson;
    }

    private static Map<String, Map<String, String>> map2JsonList(Map<String, String> map, String leftPattern, String rightPattern) {
        Map<String, Map<String, String>> listMap = new HashMap<String, Map<String, String>>();

        for (Map.Entry<String, String> entry : map.entrySet()) {

            String key = leftPattern.equals(CHARS_TWICE_UNDERLINE_PATTERN) ? removePathTag(entry.getKey(), CHARS_PATTERN) : entry.getKey();
            String value = entry.getValue();

            String primaryKey = replacePattern(entry.getKey(), rightPattern);

            String field = replacePattern(key, CHARS_UNDERLINE_PATTERN1);
            field = replacePattern(field, CHARS_UNDERLINE_PATTERN2);
            field = replacePattern(field, CHARS_UNDERLINE_PATTERN);
            Map<String, String> objMap = listMap.get(primaryKey) == null ? new HashMap<String, String>() : listMap.get(primaryKey);
            objMap.put(field, value);
            listMap.put(primaryKey, objMap);

        }
        return listMap;
    }


    public static String replacePattern(String key, String pattern) {
        return key.replaceAll(pattern, BLANK);
    }

    private static String removePathTag(String key, String subString) {
        return key.replaceFirst(subString + UNDERLINE, BLANK);
    }

    private static String headFirst(String key) {
        return key.replaceAll(UNDERLIN_RANDOMCHAR_PATTERN, BLANK);
    }


}
