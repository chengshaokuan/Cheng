package com.imooc.utils.util2.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Nyankosensei on 2017/7/20.
 */
public class EcifBaseUtil {

    public final static Logger logger = LoggerFactory.getLogger(EcifBaseUtil.class);


    /* 生产环境 */
    public static final String PROFILE_ENV_PRODUCT = "product";


    /**
     * @param obj
     * @param seed 秘钥
     * @return
     * @throws Exception
     */
    public static String generateSignInfoNew(Object obj, String seed) throws Exception {

        TreeMap<String, String> map = new TreeMap<String, String>();

        map = convertObjToMapNew(obj, map, null);

        String signStr = buildSignStr(map, seed);// 签名前字符串


        logger.info("签名前字符串:{}", signStr);


        String signInfo = md5Sign(signStr);

        logger.info("签名后字符串:{}", signInfo);

        if (!isProductEnv()) {
            //System.out.println("[Phenix]签名前字符串=" + signStr);
            //System.out.println("[Phenix]签名信息=" + signInfo);
        }
        return signInfo;
    }

    /**
     * @param obj
     * @param seed
     * @return
     * @throws Exception
     * @Title:generateSignInfoForFastPay
     * @Author:ruyiwang
     * @Date:2016年8月15日 下午7:56:32
     */
    public static String generateSignInfo(Object obj, String seed, String... exclude) throws Exception {

        TreeMap<String, String> map = new TreeMap<String, String>();

        map = convertObjToMapNew(obj, map, null);
        if (null != exclude) {
            for (String str : exclude) {
                map.remove(str);
            }
        }

        String signStr = buildSignStr(map, seed);// 签名前字符串

        String signInfo = md5Sign(signStr);

        if (!isProductEnv()) {
            //System.out.println("[Phenix]签名前字符串=" + signStr);
            //System.out.println("[Phenix]签名信息=" + signInfo);
        }
        return signInfo;
    }


    /**
     * 是否为生产环境
     *
     * @return
     */
    public static boolean isProductEnv() {
        return false;
    }

    private static void putMap(String name, Object value, TreeMap<String, String> map, String prefix) throws Exception {
        if (value == null) {
            return;
        }
        String key = (prefix == null ? name : prefix + "_" + name);

        if (key.indexOf("_serialVersionUID") != -1) {
            return;
        }

        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
            map.put(key, value.toString());
            return;
        }
        if (value instanceof Double) {
            map.put(key, formatDb2Str((Double) value));
            return;
        }
        if (value instanceof List) {
            List<?> list = (List<?>) value;
            for (int i = 0; i < list.size(); i++) {
                Object currObj = list.get(i);
                if ((currObj instanceof String) || (currObj instanceof Integer) || (currObj instanceof Long)) {
                    key = (prefix == null ? i + "_" + name : prefix + "_" + i + "_" + name);
                    map.put(key, currObj.toString());
                } else if (currObj instanceof Double) {
                    key = (prefix == null ? i + "_" + name : prefix + "_" + i + "_" + name);
                    map.put(key, formatDb2Str((Double) value));
                } else {
                    convertObjToMapNew(currObj, map, String.valueOf(i));
                }

            }
            return;
        }

        if (value instanceof Object[]) {
            Object[] list = (Object[]) value;
            for (int i = 0; i < list.length; i++) {
                Object currObj = list[i];
                if ((currObj instanceof String) || (currObj instanceof Integer) || (currObj instanceof Long)) {
                    key = (prefix == null ? i + "_" + name : prefix + "_" + i + "_" + name);
                    map.put(key, currObj.toString());
                } else if (currObj instanceof Double) {
                    key = (prefix == null ? i + "_" + name : prefix + "_" + i + "_" + name);
                    map.put(key, formatDb2Str((Double) value));
                } else {
                    convertObjToMapNew(currObj, map, String.valueOf(i));
                }
            }
            return;
        }
    }

    public static TreeMap<String, String> convertObjToMapNew(Object obj, TreeMap<String, String> map, String prefix)
            throws Exception {
        if (obj == null) {
            return map;
        }

        if (obj instanceof Map) {
            Map<?, ?> m = (Map<?, ?>) obj;
            for (Iterator<?> i = m.keySet().iterator(); i.hasNext(); ) {
                String key = String.valueOf(i.next());
                putMap(key, m.get(key), map, prefix);
            }

        } else {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(obj);
                putMap(field.getName(), value, map, prefix);
            }
        }
        map.remove("__equalsCalc");
        map.remove("__hashCodeCalc");
        map.remove("serialVersionUID");
        map.remove("signInfo");// 本身的签名信息不参与签名
        map.remove("typeDesc");// 本身的签名信息不参与签名

        return map;
    }

    /**
     * map 转换成键值对的字符串 生成key1=value1&key2=value2的待签名字符串,最后加上seed
     *
     * @param map
     * @param seed
     * @return
     */
    public static String buildSignStr(TreeMap<String, String> map, String seed) {

        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<String, String>> es = map.entrySet();
        Iterator<Map.Entry<String, String>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            String k = entry.getKey();
            String v = entry.getValue();
            if ((null != v) && !"".equals(v) && !"null".equalsIgnoreCase(v) && !"signInfo".equals(k) && !"seed".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append(seed);
        return sb.toString();
    }

    /**
     * 对字符串签名
     *
     * @param signStr
     * @return
     */
    public static String md5Sign(String signStr) {

        byte[] signInfo = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            signInfo = md.digest(signStr.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return byteArrayToHexString(signInfo);

    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"};

    public static String formatDb2Str(Double d) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(d);
    }


    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    /**
     * 将来源系统的证件编码转换成目标系统的证件编码
     * @param srcSysTpCd 来源系统代码
     * @param srcDicItemCd 来源系统的证件编码
     * @param tarSysTpCd 目标系统代码
     * @return 目标系统的证件编码
     */
//    public static String transformCardTypeCode(String srcSysTpCd, String srcDicItemCd, String tarSysTpCd){
//        String trustType = "";
//
//        SysCodeMappingMapper sys = SpringContextUtils.getBean("sysCodeMappingMapper");
//        SysCodeMappingKey key=new SysCodeMappingKey();
//        key.setDicCd("3");
//        key.setSrcSysTpCd(srcSysTpCd);
//        key.setTarSysTpcd(tarSysTpCd);
//        key.setSrcDicItemCd(srcDicItemCd);
//
//        SysCodeMappingVO mapper = sys.selectByPrimaryKey(key);
//        if(mapper != null)
//            trustType = sys.selectByPrimaryKey(key).getTarDicItemCd();
//
//        return trustType;
//    }

/*	public static void main(String[] args) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("amount", "2.00");
		map.put("userId", "002");

		map.put("signInfo", BaseUtil.generateSignInfoNew(map, "seed"));

		System.out.println(map.get("signInfo"));
	}*/

}
