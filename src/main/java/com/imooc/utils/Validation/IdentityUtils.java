package com.imooc.utils.Validation;

import java.text.ParseException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:中国大陆的身份证检验算法，以及15位和18位身份证的相互转换。
 * @author WangShutao
 * @version 1.0
 * <pre>
 * Modification History:
 * Date         Author       Version     Description
------------------------------------------------------------------
 * 2012-12-20      WangShutao    1.0        1.0 Version
 * </pre>
 */
public class IdentityUtils {
	/**
	 * Description:15位身份证号码转化为18位的身份证。如果是18位的身份证则直接返回，不作任何变化。
	 * @param idCard
     *            15位的有效身份证号码
	 * @return idCard18 返回18位的有效身份证
	 * @throws
	 * @Author WangShutao
	 * Create Date: 2012-12-20 下午3:57:19
	 */
	public static String id15to18(String idCard) {
		idCard = idCard.trim();
		StringBuffer idCard18 = new StringBuffer(idCard);
		// 校验码值
		char[] checkBit = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3',
				'2' };
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
			int indexOfCheckBit = sum % 11; // 取模
			idCard18.append(checkBit[indexOfCheckBit]);
		}
		return idCard18.toString();
	}

	/**
	 * 转化18位身份证位15位身份证。如果输入的是15位的身份证则不做任何转化，直接返回。
	 * 
	 * @param idCard
	 *            18位身份证号码
	 * @return idCard15
	 */
	public static String id18to15(String idCard) {
		idCard = idCard.trim();
		StringBuffer idCard15 = new StringBuffer(idCard);
		if (idCard != null && idCard.length() == 18) {
			idCard15.delete(17, 18);
			idCard15.delete(6, 8);
		}
		return idCard15.toString();
	}

	/**
	 * Description:
	 * @param idCard 原始身份证号码。
	 * @return boolean
	 * @throws
	 * @Author WangShutao
	 * Create Date: 2012-12-20 下午3:59:49
	 */
	public static boolean checkIdentity(String idCard) {
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
	 * Description:把身份证转成对应的18位或15位
	 * @param idno 原始身份证号码。
	 * @return HashMap<String,Object>
	 * @throws
	 * @Author WangShutao
	 * Create Date: 2012-12-20 下午4:00:14
	 */
	public static HashMap<String, Object> getIdnoToMap(String idno) {
		String idno18 = idno;
		String idno15 = idno;
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			if (idno.length() == 15) {
				idno15 = idno.toUpperCase();
				idno18 = IdentityUtils.id15to18(idno.toUpperCase());
			} else if (idno.length() == 18) {
				idno18 = idno.toUpperCase();
				idno15 = IdentityUtils.id18to15(idno.toUpperCase());
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("idno18", idno);
			map.put("idno15", idno);
		}
		map.put("idno18", idno18);
		map.put("idno15", idno15);
		return map;
	}

	/**
	 * Description:根据身份证获取出生日期
	 * @param idno 身份证
	 * @return String
	 * @throws ParseException 转换异常。
	 * @Author shaofengwang
	 * Create Date: 2012-12-20 下午4:01:04
	 */
	public static String getBrithday(String idno) throws ParseException {
		idno = id15to18(idno);
		String birthday = idno.substring(6, 14);
		return birthday;
	}

	/**
	 * Description:根据身份证获取性别
	 * @param idno 身份证
	 * @return int
	 * @throws
	 * @Author shaofengwang
	 * Create Date: 2012-12-20 下午4:01:27
	 */
	public static int getGender(String idno) {
		int sex = 1;// 男
		idno = id15to18(idno);
		String id17 = idno.substring(16, 17);
		if (Integer.parseInt(id17) % 2 == 0) {
			sex = 0; // 女
		}
		return sex;
	}

	/**
	 * Description:根据身份证获取户籍地址:前2位是省,3、4位是地级市,5、6位是县区
	 * @param idno 身份证
	 * @return String[]
	 * @throws
	 * @Author shaofengwang
	 * Create Date: 2012-12-20 下午4:01:59
	 */
	public static String[] getAddressCode(String idno) {
		idno = id15to18(idno);
		String address = idno.substring(0, 6);
		// 完整的六位是县区
		String area = address;
		// 市:截取前四位,后两位补零
		String city = address.substring(0, 4).concat("00");
		// 省:截取前两位位,后四位补零
		String province = address.substring(0, 2).concat("0000");
		return new String[] { area, city, province };
	}
}
