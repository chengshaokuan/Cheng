package com.imooc.utils.util2.utils;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * Created by xiaguangmeng on 17/7/3.
 */
public class CustomerUpdateMergeUtil {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerUpdateMergeUtil.class);

    public static JSONObject modifyChunk(JSONObject oldJson, JSONObject newJson) {
        Iterator<String> newKeys = newJson.keys();
        while (newKeys.hasNext()) {
            String key = newKeys.next();
            Object value = newJson.get(key);

            JsonPathUtil.JsonPathKey jsonPathKey = JsonPathUtil.JsonPathKey.valuesOf(key);

            if (null != jsonPathKey) {
                switch (JsonPathUtil.JsonPathKey.valuesOf(key).getLayer()) {
                    case 0:
                        oldJson.put(key, mergeNormalObj(oldJson.get(key), (JSONObject) value));
                        break;
                    case 1:
                    case 2:
                        oldJson.put(key, value);
                        break;
                    default:
                        LOG.info("[customer]-[merge modifyChunk]-[error]:{}", jsonPathKey);
                        break;
                }
            } else {
                oldJson.put(key, value);
            }
        }
        return oldJson;
    }

    private static JSONObject mergeNormalObj(Object oldJs, JSONObject newJs) {
        if (oldJs instanceof JSONObject) {
            ((JSONObject) oldJs).putAll(newJs);
            return (JSONObject) oldJs;
        }
        return newJs;
    }
}
