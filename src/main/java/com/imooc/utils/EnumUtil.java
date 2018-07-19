package com.imooc.utils;

        import com.imooc.enums.CodeEnum;

        import java.io.Serializable;

/**
 * Created by
 * 2018-04-16 8:36
 */
public class EnumUtil implements Serializable{

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
