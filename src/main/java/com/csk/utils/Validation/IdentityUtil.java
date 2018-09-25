package com.csk.utils.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: Cheng
 * @description: 中国大陆的身份证检验算法，以及15位和18位身份证的相互转换。
 * @author: Mr.Cheng
 * @create: 2018-08-27 10:18
 **/
public class IdentityUtil {

    /**
     * @Description: 15位身份证号码转化为18位的身份证。如果是18位的身份证则直接返回，不作任何变化。
     * @param: idCard 15位的有效身份证号码
     * @return: java.lang.String 返回18位的有效身份证
     * @Author: Mr.Cheng
     * @Date: 10:20 2018/8/27
     */
    public static String id15to18 (String idCard) {
        idCard = idCard.trim();
        StringBuffer idCard18 = new StringBuffer(idCard);
        // 校验码值
        char[] checkBit = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        int sum = 0;
        // 15位的身份证
        if (idCard != null && idCard.length() == 15) {
            idCard18.insert(6, "19");
            for (int index = 0; index < idCard18.length(); index++) {
                char c = idCard18.charAt(index);
                int ai = Integer.parseInt(new Character(c).toString());
                // 加权因子的算法
                int wi = ((int) Math.pow(2, idCard18.length() - index)) % 11;
                sum = sum + ai * wi;
            }
            // 取模
            int indexOfCheckBit = sum % 11;
            idCard18.append(checkBit[indexOfCheckBit]);
        }
        return idCard18.toString();
    }

    /**
     * @Description: 转化18位身份证位15位身份证。如果输入的是15位的身份证则不做任何转化，直接返回。
     * @param: idCard  18位身份证号码
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 11:10 2018/8/27
     */
    public static String id18to15 (String idCard) {
        idCard = idCard.trim();
        StringBuffer idCard15 = new StringBuffer(idCard);
        if (idCard != null && idCard.length() == 18) {
            idCard15.delete(17, 18);
            idCard15.delete(6, 8);
        }
        return idCard15.toString();
    }

    /**
     * @Description: 原始身份证号码。
     * @param: idCard
     * @return: boolean
     * @Author: Mr.Cheng
     * @Date: 11:11 2018/8/27
     */
    public static boolean checkIdentity (String idCard) {
        boolean isIDCard = false;
        Pattern pattern = Pattern.compile("\\d{15}|\\d{17}[x,X,0-9]");
        Matcher matcher = pattern.matcher(idCard);
        if (matcher.matches()) {// 可能是一个身份证
            isIDCard = true;
            if (idCard.length() == 18) {// 如果是18的身份证，则校验18位的身份证。15位的身份证暂不校验
                String idCard15 = id18to15(idCard);
                String idCard18 = id15to18(idCard15);
                if (!idCard.equalsIgnoreCase(idCard18)) {
                    isIDCard = false;
                }
            } else if (idCard.length() == 15) {
                isIDCard = true;
            } else {
                isIDCard = false;
            }
        }
        return isIDCard;
    }

    /**
     * @Description: 根据身份证获取出生日期
     * @param: idno 身份证
     * @return: java.lang.String
     * @Author: Mr.Cheng
     * @Date: 11:14 2018/8/27
     */
    public static String getBrithday (String idno) {
        idno = id15to18(idno);
        String birthday = idno.substring(6, 14);
        return birthday;
    }

    /**
     * @Description: 根据身份证获取性别; 1:男,0:女
     * @param: idno 身份证
     * @return: int
     * @Author: Mr.Cheng
     * @Date: 11:15 2018/8/27
     */
    public static int getGender (String idno) {
        int sex = 1;// 男
        idno = id15to18(idno);
        String id17 = idno.substring(16, 17);
        if (Integer.parseInt(id17) % 2 == 0) {
            sex = 0; // 女
        }
        return sex;
    }

    /**
     * @Description: 根据身份证获取户籍地址:前2位是省,3、4位是地级市,5、6位是县区
     * @param: idno 身份证
     * @return: java.lang.String[]
     * @Author: Mr.Cheng
     * @Date: 11:15 2018/8/27
     */
    public static String[] getAddressCode (String idno) {
        idno = id15to18(idno);
        String address = idno.substring(0, 6);
        // 完整的六位是县区
        String area = address;
        // 市:截取前四位,后两位补零
        String city = address.substring(0, 4).concat("00");
        // 省:截取前两位位,后四位补零
        String province = address.substring(0, 2).concat("0000");
        return new String[]{area, city, province};
    }

}
