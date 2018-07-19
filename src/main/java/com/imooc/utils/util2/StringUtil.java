package com.imooc.utils.util2.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author 201603030496
 * 
 */
public class StringUtil {
	
	/**
	 *Description:拼接transSn
	 *@author  shiqi
	 *@date 2017-12-28
	 */
	public static String getEcifTransSn(String idt, String sysSign){
		String strDate = TimeFormatUtil.getCurrentTimeStr_YMD();
        StringBuilder sysSignResult = new StringBuilder("0");
		sysSignResult.append(sysSign);
        StringBuilder transSn  = new StringBuilder(idt);
        transSn.append(sysSignResult.toString()).append(strDate).append("000000");
		return transSn.toString();
	}
	
	/**
	 * Description:判断字符串是否为空
	 * @author shiqi
	 * @date 2017-12-28
	 */
	public static boolean isBlank(String input) {
		return input == null || "".equals(input) || input.length() == 0 || input.trim().length() == 0;
	}



	/**
	 * Description:判断字符串不为空
	 * @author shiqi
	 * @date 2017-12-28
	 */
	public static boolean isNotBlank(String input) {
		return !isBlank(input);
	}

	/**
	 * Description:将bigdecimal转换为字符串
	 * @author shiqi
	 * @date 2017-12-28
	 */
	public static String bigDecimalToString(BigDecimal dec) {
		String str = "";
		if (dec != null) {
			str = dec.toString();
		}
		return str;
	}

	/**
	 * Description:字符串半角转全角
	 * @author shiqi
	 * @date 2017-12-28
	 */
	public static String ToSBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);

			}
		}
		return new String(c);
	}

	/**
	 * Description:字符串全角转半角
	 * @author shiqi
	 * @date 2017-12-28
	 */
	public static String ToDBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		String returnString = new String(c);
		return returnString;
	}

	/**
	 * 字符串比较
	 * @author shiqi
	 * @date 2017-12-28
	 */
	public static boolean strCompare(String first,String second){
		return (first==null?"":first.trim()).equals(second==null?"":second.trim());
	}

	/**
	 * 检测字符是否是大写
	 * @author shiqi
	 * @date 2017-12-28
	 */
	public static boolean checkUpperCase(String number){
		String upperNumber = number.toUpperCase();
		if(upperNumber.equals(number)){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * String 转 list
	 * @author shiqi
	 * @date 2017-12-28
	 */

	public static List<String> stringToList(String string){
		List<String> result = null;
		if(string == null) return result;
		string = string.substring(string.indexOf("[")+1,string.indexOf("]"));
		result = Arrays.asList(string.split(","));
		return  result;
	}

	public static void main(String[] args) {
		String strSBC = "ｍａｏｘｉｏｎｇ";
		String strDBC = "test";
		System.out.println(ToDBC(strSBC));
		System.out.println(ToSBC(strDBC));
		if (strSBC.equals(ToDBC(strSBC))) {
			System.out.println("相同");
		} else {
			System.out.println("不同");
		}
		if (strDBC.equals(ToDBC(strDBC))) {
			System.out.println("相同");
		} else {
			System.out.println("不同");
		}
	}
}
