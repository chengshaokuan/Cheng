package com.imooc.utils.util2.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.creditease.panda.engine.utils.CustomerJsonUnFlattenUtil.replacePattern;
import static com.creditease.panda.engine.utils.JsonPathUtil.*;


/**
 * Created by xiaguangmeng on 17/6/29.
 */
public class CustomerJsonFlattenUtil {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerJsonUnFlattenUtil.class);


    /**
     * 多层json  展开为Map<String, String>  map的key为jsonPath_jsonkey_...形式
     * json数组必须有一个key作为区分该条数据
     *
     * @param jsObj
     * @return
     */
    public static Map<String, String> flattenJson(JSONObject jsObj) {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, Map<String, String>> tagMap = new HashMap<String, Map<String, String>>();

        String systemSource = getSystemSource(jsObj);
        map.putAll(Json2Map(null, null, jsObj, systemSource, tagMap));
        return map;
    }

    /**
     * 获得系统来源，用于记录联系人和联系方式最初系统，合并时改字段不被更改
     * @param jsObj
     * @return
     */
    private static String getSystemSource(JSONObject jsObj) {
        Object generals = jsObj.get(JsonPathKey.GENERALS.getContext());
        if (null == generals) {
            LOG.error("no systemSource !");
            return ZERO_STR;
        } else {
            return ((JSONObject) ((JSONArray) generals).get(0)).get(SYSTEM_SOURCE).toString();
        }
    }

    /**
     * JSONObject 转换为 map，isNewest（是否最新）和isMajorJob（是否主职业）需特殊处理，若该list存在任意其中之一字段且为1，则历史的该list信息的所有该字段变为0，以本次推送为最新
     * @param path
     * @param hashKey
     * @param jsObj
     * @param systemSource
     * @param tagMap
     * @return
     */
    private static Map<String, String> Json2Map(String path, String hashKey, JSONObject jsObj, String systemSource, Map<String, Map<String, String>> tagMap) {
        Map<String, String> map = new HashMap<String, String>();
        Iterator<String> i = jsObj.keys();

        while (i.hasNext()) {
            String key = i.next();
            //json url
            String jsonPath = JsonPath(path, key);

            //组合现在的key  生成的hashKey
            String newHKey = generateHashKey(hashKey, key);
            Object value = jsObj.get(key);

            if (value instanceof JSONArray && JsonPathUtil.getArrayPathHashKey(key).contains(DO_NOTHING)) {
                map.put(key, value.toString());
            } else if (value instanceof JSONArray) {
                map.putAll(Json2Map(jsonPath, newHKey, (JSONArray) value, systemSource, tagMap));
            } else if (value instanceof JSONObject) {
                map.putAll(Json2Map(jsonPath, newHKey, (JSONObject) (value), systemSource, tagMap));
            } else if ((key.equals(IS_NEWEST) || key.equals(IS_MAJOR_JOB)) && equalOne(value)) {
                String tagKey = replacePattern(hashKey, UNDERLINE_CHARS_PATTERN);
                Map<String, String> m = tagMap.get(tagKey);
                m = null == m ? new HashMap<String, String>() : m;
                m.put(newHKey, value.toString());
                tagMap.put(tagKey, m);
            } else if (!key.equals(IS_NEWEST) && !key.equals(IS_MAJOR_JOB)) {
                map.put(newHKey, value.toString());
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            map.put(TAG_INDEX, mapper.writeValueAsString(tagMap));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            LOG.error("[customer]-[flatten]-[exception]:{}", e.getMessage());
        }

        return map;
    }

    /**
     * JSONArray 转map  需确认每个list的唯一识别信息（改字段必须存在否则报错）
     * @param path
     * @param hashKey
     * @param jsArray
     * @param systemSource
     * @param tagMap
     * @return
     */
    private static Map<String, String> Json2Map(String path, String hashKey, JSONArray jsArray, String systemSource, Map<String, Map<String, String>> tagMap) {
        Map<String, String> map = new HashMap<String, String>();
        Iterator jsIterator = jsArray.iterator();

        while (jsIterator.hasNext()) {
            JSONObject j = (JSONObject) jsIterator.next();

            //根据contactType类型做分类 合并时根据联系方式类型合并
            String curHashKey = hashKey;
            if (j.containsKey(CONTACT_TAPE) && !hashKey.equals(JsonPathKey.CONTACTS.getContext())) {
                curHashKey += UNDERLINE + j.getString(CONTACT_TAPE);
            }
            //记录list的最初系统来源（合并时该字段不变）
            if (hashKey.equals(JsonPathKey.CONTACTS.getContext()) || hashKey.equals(JsonPathKey.CONTACT_WAYS.getContext())
                    || hashKey.equals(JsonPathKey.CONTACT_WAYS.getContext())) {
                j.put(CONTACT_SOURCE, systemSource);
            }
            List<String> nextKey = JsonPathUtil.getArrayPathHashKey(path);

            //以两种方式作为主键的情况  若一个主键值不存在  则以 后一个为主键
            String primaryKey = getPrimaryKey(j, nextKey);
            primaryKey = !primaryKey.isEmpty() ? primaryKey : getPrimaryKey(j, JsonPathUtil.getArrayPathHashKey(JsonPath(path, nextKey.get(0))));

            String newHKey = generateHashKey(curHashKey, primaryKey);
            map.putAll(Json2Map(path, newHKey, j, systemSource, tagMap));
        }
        return map;
    }


    private static String JsonPath(String path, String key) {
        return null == path ? key : (path + POINT + key);
    }

    private static String generateHashKey(String hashKey, String key) {
        if (hashKey == null) return key;
        else if (key == null) return hashKey;
        else return hashKey + UNDERLINE + key;
    }

    /**
     * 获得识别list唯一性的值
     * @param j
     * @param list
     * @return
     */
    private static String getPrimaryKey(final JSONObject j, List<String> list) {
        final StringBuilder currentPKey = new StringBuilder();
        CollectionUtils.forAllDo(list, new Closure() {
            @Override
            public void execute(Object o) {
                Object key = j.get(o);
                if (null != key) {
                    currentPKey.append(key.toString());
                }
            }
        });
        return currentPKey.toString();
    }

    private static boolean equalOne(Object o) {
        return o.equals(ONE_NUM) || o.equals(ONE_STR);
    }
}
