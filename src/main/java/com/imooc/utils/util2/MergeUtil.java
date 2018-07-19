package com.imooc.utils.util2;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

import static com.creditease.panda.engine.utils.JsonPathUtil.*;


/**
 * Created by xiaguangmeng on 17/5/9.
 */
public class MergeUtil {

    /**
     * 新老客户map合并
     * 根据key进行合并，若非空非null且非ORIGINAL_SYSTEM则覆盖。其中TAG_INDEX字段存储的为isNewest 或 isMajorJob 需特殊处理
     * @param oldMap
     * @param newMap
     * @return
     */
    public static Map<String, String> merge(Map<String, String> oldMap, Map<String, String> newMap) {

        ObjectMapper mapper = new ObjectMapper();

        for (Map.Entry<String, String> entry : newMap.entrySet()) {
            String newMapKey = entry.getKey();
            String newValue = entry.getValue();
            String oldValue = oldMap.get(newMapKey);

            if (newMapKey.equals(TAG_INDEX)) {
                try {
                    JSONObject newTagMap = mapper.readValue(newValue, JSONObject.class);
                    JSONObject oldTagMap = mapper.readValue(oldValue, JSONObject.class);

                    oldTagMap.putAll(newTagMap);
                    oldMap.put(newMapKey, mapper.writeValueAsString(oldTagMap));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (isCover(newMapKey, newValue, oldValue)) {
                oldMap.put(newMapKey, newValue);
            }
        }
        return oldMap;
    }

    private static Boolean isCover(String newMapKey, String newValue, String oldValue) {
        return !(StringUtils.isBlank(newValue) || (newMapKey.endsWith(CONTACT_SOURCE) && oldValue != null));
    }
}
