package com.hsl.api.utils;

import java.security.MessageDigest;

/**
 * @author peng.ding
 * @ClassName: MD5Util
 * @Description: MD5加密解密工具类
 * @date 2018-12-14 10:18
 */

public class MD5Util {
    private static final String SALT = "CDJCY";

    /**
     * 使用系统指定的盐加密
     *
     * @param password
     * @return
     */
    public static String encode(String password) {
        return MD5Util.encode(SALT, password);
    }

    /**
     * 使用传入的盐加密
     *
     * @param salt
     * @param password
     * @return
     */
    public static String encode(String salt, String password) {
        password = password + salt;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 用于获取一个String的md5值
     * @param
     * @return
     */
    public static String getMd5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        byte[] bs = md5.digest(str.getBytes());
        StringBuilder sb = new StringBuilder(40);
        for(byte x:bs) {
            if((x & 0xff)>>4 == 0) {
                sb.append("0").append(Integer.toHexString(x & 0xff));
            } else {
                sb.append(Integer.toHexString(x & 0xff));
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {

//        System.out.println(MD5Util.encode("","123456").toUpperCase());
//        System.out.println(Base64.encodeToString("hsl:123456".getBytes(),16));
//        try {
//            System.out.println(SecuritySHA1Utils.shaEncode("111111"));
//            System.out.println(SecuritySHA1Utils.shaEncode("123456"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("end");
        int i = 0;
        long a = -8l;
        if(a<0) a-=24;
        i = (int) (a/24);
        System.out.println(i);
    }
}
