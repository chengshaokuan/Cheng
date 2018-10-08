/**
 * Project Name:cn.liuyiyou.common
 * File Name:EnumSerializer.java
 * Package Name:cn.liuyiyou.common.utils
 * Date:2017年7月25日下午4:30:41
 * Copyright (c) 2017, liuyiyou.cn All Rights Reserved.
 */
package com.csk.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.beanutils.PropertyUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: Cheng
 * @description: Enum序列化工具类
 * @author: Mr.Cheng
 * @create: 2018-09-05 17:03
 **/
@SuppressWarnings("rawtypes")
public class EnumSerializer extends JsonSerializer<Enum> {

    @Override
    public void serialize (Enum value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        Map<String, Object> props = null;
        try {
            props = PropertyUtils.describe(value);
            props.remove("class");
            props.remove("declaringClass");
        } catch (Exception e) {
            props = new HashMap<String, Object>();
        }
        props.put("name", value.name());
        jgen.writeObject(props);
    }
}

