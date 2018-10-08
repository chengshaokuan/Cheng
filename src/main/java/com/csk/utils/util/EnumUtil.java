package com.csk.utils.util;

import com.csk.enums.CodeEnum;

import java.io.Serializable;

/**
 * @program: Cheng
 * @description: Enum工具类
 * @author: Mr.Cheng
 * @create: 2018-09-05 17:03
 **/
public class EnumUtil implements Serializable {

    public static <T extends CodeEnum> T getByCode (Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
