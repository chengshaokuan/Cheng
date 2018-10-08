package com.csk.utils.util;


import java.util.Random;

/**
 * @program: Cheng
 * @description: 工具类
 * @author: Mr.Cheng
 * @create: 2018-09-05 17:03
 **/
public class KeyUtil {

    /**
     * @Description: 生成唯一的主键
     * 格式: 时间+随机数
     * @param:
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 15:16 2018/10/8
     */
    public static synchronized String genUniqueKey () {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        System.out.println(System.currentTimeMillis() + String.valueOf(number));
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
