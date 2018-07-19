package com.imooc.utils.Math;

import java.text.DecimalFormat;

/**
 * Created by Nyankosensei on 2017/7/7.
 */
public class NumberFormatUtil {

    public static String formatDb2Str(Double db) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(db);
    }
}
