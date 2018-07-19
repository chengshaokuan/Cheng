package com.imooc.utils.JsonUtil;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by xiaguangmeng on 17/5/27.
 */
public class ReadJsonUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ReadJsonUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode parseString2JsonNode(String data) {

        try {
            objectMapper.readTree(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把JavaBean转换为json字符串
     *
     * @param object
     * @return
     */
    public static String parseObject2String(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            LOG.error("parseObject2String error!", e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

}
