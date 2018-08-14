package com.imooc.utils.Encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Nyankosensei on 2017/7/7.
 */
public class CipherUtil {

    private static final String MD5 = "MD5";
    private static final String UTF_8 = "UTF-8";
    private static final String []hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"};

    public static String md5(String signStr) {
        byte[] signInfo = null;

        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            signInfo = md.digest(signStr.getBytes(UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return byteArray2HexString(signInfo);
    }

    private static String byteArray2HexString(byte []b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(byte2HexString(b[i]));
        }
        return sb.toString();

    }

    private static String byte2HexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
