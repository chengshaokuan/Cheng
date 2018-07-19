package com.imooc.utils.util2;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by Nyankosensei on 2017/7/6.
 */
public class TimeFormatUtil {


    private static final DateTimeFormatter dateTimeFormatter_Y_M_D_H_M_S = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter dateTimeFormatter_YMDHMS = DateTimeFormat.forPattern("yyyyMMddHHmmss");

    private static final DateTimeFormatter dateTimeFormatter_Y_M_D = DateTimeFormat.forPattern("yyyy-MM-dd");
    private static final DateTimeFormatter dateTimeFormatter_YMD = DateTimeFormat.forPattern("yyyyMMdd");

    private static final DateTimeFormatter dateTimeFormatter_H_M_S = DateTimeFormat.forPattern("HH:mm:ss");
    private static final DateTimeFormatter dateTimeFormatter_HMS = DateTimeFormat.forPattern("HHmmss");


    public static String getTimeStr_Y_M_D_H_M_S(DateTime dateTime) {
        return dateTime.toString(dateTimeFormatter_Y_M_D_H_M_S);
    }

    public static String getCurrentTimeStrY_M_D_H_M_S() {
        return getTimeStr_Y_M_D_H_M_S(DateTime.now());
    }

    public static String getTimeStr_YMDHMS(DateTime dateTime) {
        return dateTime.toString(dateTimeFormatter_YMDHMS);
    }

    public static String getCurrentTimeStr_YMDHMS() {
        return getTimeStr_YMDHMS(DateTime.now());
    }

    public static String getTimeStr_Y_M_D(DateTime dateTime) {
        return dateTime.toString(dateTimeFormatter_Y_M_D);
    }

    public static String getCurrentTimeStr_Y_M_D() {
        return getTimeStr_Y_M_D(DateTime.now());
    }

    public static String getTimeStr_YMD(DateTime dateTime) {
        return dateTime.toString(dateTimeFormatter_YMD);
    }

    public static String getCurrentTimeStr_YMD() {
        return getTimeStr_YMD(DateTime.now());
    }

    public static String getTimeStr_H_M_S(DateTime dateTime) {
        return dateTime.toString(dateTimeFormatter_H_M_S);
    }

    public static String getCurrentTimeStr_H_M_S() {
        return getTimeStr_H_M_S(DateTime.now());
    }

    public static String getTimeStr_HMS(DateTime dateTime) {
        return dateTime.toString(dateTimeFormatter_HMS);
    }

    public static String getCurrentTimeStr_HMS() {
        return getTimeStr_HMS(DateTime.now());
    }

    public static Date getDate(String str) {
        return new DateTime(str).toDate();
    }
}
