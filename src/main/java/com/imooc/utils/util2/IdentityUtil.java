package com.imooc.utils.util2.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mengshuai on 2017/7/12.
 */
public class IdentityUtil {

    private static final int ID_LENGTH = 18;
    private static final int[] weightFactor = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1};  //加权因子
    private static final char[] modList = new char[]{'1','0','X','9','8','7','6','5','4','3','2'}; //对应0~10的余数列表

    /**
     *
     *Description:身份证校验，为了保持与交易系统一致，不对身份证号码进行去空处理
     *@param
     *@return
     */
    public static boolean IdCardValid(String strId){
        if(!lengthValid(strId))
            return false;
        if(!lastBitValid(strId))
            return false;
        return true;
    }

    /**
     *Description:长度校验
     *@param
     *@return
     */
    private static boolean lengthValid(String strId){
        if(strId==null)
            return false;
        if(strId.trim().length()!=ID_LENGTH)
            return false;
        return true;
    }

    /**
     *
     *Description:最后一位校验位校验
     *@param
     *@return
     */
    private static boolean lastBitValid(String strId){
        int iSum=0;
        byte[] byValue = strId.getBytes();
        for(int i=0; i<byValue.length; i++)
            byValue[i] -= 48;
        for(int i=0; i<strId.length()-1; i++){
            iSum += byValue[i]*weightFactor[i];
        }
        iSum = iSum%11;
        char strValidBit =  modList[iSum];
        char lastBit = strId.toUpperCase().charAt(strId.length()-1);
        if(strValidBit != lastBit)
            return false;
        return true;
    }

    /**
     * @Description 从身份证中提取出生日期
     *
     * @param idNumber
     *
     */
    public static Date getBirthday(String idNumber) {
       if(IdCardValid(idNumber)) {
           String date = idNumber.substring(6, 14);
           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
           try {
               return simpleDateFormat.parse(date);
           } catch (ParseException e) {
               e.printStackTrace();
               return null;
           }
       }

       return null;
    }
}
