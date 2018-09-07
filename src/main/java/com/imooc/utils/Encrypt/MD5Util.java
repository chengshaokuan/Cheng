package com.imooc.utils.Encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;


/**
 * @program: Cheng
 * @description:MD5加密
 * @author: Mr.Cheng
 * @create: 2018-09-06 17:06
 **/
public class MD5Util {

    private static final Logger LOGGER = LoggerFactory.getLogger(MD5Util.class);

    private static final String MD5 = "MD5";
    private static final String UTF_8 = "UTF-8";

    private static byte[] md5 (String s) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(MD5);
            md.reset();
            //执行加密
            md.update(s.getBytes(UTF_8));
            //加密结果
            return md.digest();
        } catch (Exception e) {
            LOGGER.error("MD5 Error...{}", e);
        }
        return null;
    }

    private static final String toHex (byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;
        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
//            buf.append(Long.toString(hash[i] & 0xff, 16));
            buf.append(Integer.toHexString(hash[i] & 0xff));

        }
        return buf.toString();
    }

    public static String cipher (String string) {
        try {
            //如果为空，则返回""
            String s = (string == null) ? "" : string;
            byte[] md5 = md5(string);
            byte[] bytes = toHex(md5).getBytes(UTF_8);
            return new String(bytes, UTF_8);
        } catch (Exception e) {
            LOGGER.error("not supported charset...{}", e);
            return string;
        }
    }

    /**
     * 对密码按照用户名，密码，盐进行加密
     *
     * @param username 用户名
     * @param password 密码
     * @param salt     盐就是一个随机生成的字符串
     * @return
     */
    public static String encryptPassword (String username, String password, String salt) {
        return MD5Util.cipher(username + password + salt);
    }

    /**
     * 对密码按照密码，盐进行加密
     *
     * @param password 密码
     * @param salt     盐
     * @return
     */
    public static String encryptPassword (String password, String salt) {
        return MD5Util.cipher(password + salt);
    }

    public static void main (String[] args) {
        String s = new String("18317570940");
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + cipher(s));
    }
}
