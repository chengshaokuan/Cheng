package com.imooc.utils.math;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by
 * 2017-07-09 16:56
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    /**
     * @Description: 比较2个数值是否相等
     * @param: d1
     * @param: d2
     * @return: java.lang.Boolean
     * @Author: Mr.Cheng
     * @Date: 15:06 2018/8/14
     */
    public static Boolean equals (Double d1, Double d2) {
        Double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Description:
     * @param: db
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 15:05 2018/8/14
     */
    public static String formatDb2Str (Double db) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(db);
    }

    /**
     * @Description: 将一个double类型的金额四舍五入
     * @param: d  你需要四舍五入的金额
     * @return: double 四舍五入后的金额
     * @Author: Mr.Cheng
     * @Date: 15:20 2018/8/14
     */
    public static double round (double d) {
        return new BigDecimal(d).setScale(2, 4).doubleValue();
    }

    /**
     * @Description: 将一个BigDecimal类型的金额四舍五入
     * @param: b  你需要四舍五入的金额
     * @return: java.math.BigDecimal  四舍五入后的金额
     * @Author: Mr.Cheng
     * @Date: 15:21 2018/8/14
     */
    public static BigDecimal round (BigDecimal b) {
        if (b == null) {
            throw new NullPointerException("b");
        }
        return b.setScale(2, 4);
    }

    /**
     * @Description: 将一个float类型的金额四舍五入
     * @param: f  你需要四舍五入的金额
     * @return: float  四舍五入后的金额
     * @Author: Mr.Cheng
     * @Date: 15:25 2018/8/14
     */
    public static float round (float f) {
        return new BigDecimal(f).setScale(2, 4).floatValue();
    }
}
