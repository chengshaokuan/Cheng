package com.csk.utils.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by 程少宽
 * 2017-06-11 19:12
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
    public static void main (String[]args){
        String s = KeyUtil.genUniqueKey();
        System.out.println(s);
        GregorianCalendar gc = new GregorianCalendar();
        System.out.println(gc.get(Calendar.YEAR));
    }
}
