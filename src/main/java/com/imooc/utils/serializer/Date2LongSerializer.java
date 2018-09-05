package com.imooc.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @Description: 自定义序列
 * @param: null
 * @return:
 * @Author: Mr.Cheng
 * @Date: 16:20 2018/9/5
 */
public class Date2LongSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize (Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(date.getTime() / 1000);
    }
}
