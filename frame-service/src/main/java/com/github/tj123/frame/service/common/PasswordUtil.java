package com.github.tj123.frame.service.common;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Created by TJ on 2017/1/6.
 */
public class PasswordUtil {

    /**
     * 加密因子长度
     */
    private static final Integer SALT_LENGTH = 12;

    /**
     * 16 进制比较串
     */
    private static final String HEX_NUMS_STR = "0123456789ABCDEF";


    /**
     * 对密码加密
     *
     * @param password
     * @return
     */
    public static String encrypt(String password) throws Exception {
        byte[] pwd = null;
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        MessageDigest md = null;
        md = MessageDigest.getInstance("MD5");
        md.update(salt);
        md.update(password.getBytes("UTF-8"));
        byte[] digest = md.digest();
        pwd = new byte[digest.length + SALT_LENGTH];
        System.arraycopy(salt, 0, pwd, 0, SALT_LENGTH);
        System.arraycopy(digest, 0, pwd, SALT_LENGTH, digest.length);
        return byteToHexString(pwd);
    }

    /**
     * 判断密码是否匹配
     *
     * @param password
     * @param dbPassword
     * @return
     */
    public static boolean isMatch(String password, String dbPassword) throws Exception {
        byte[] pwdInDb = hexStringToByte(dbPassword);
        byte[] salt = new byte[SALT_LENGTH];
        System.arraycopy(pwdInDb, 0, salt, 0, SALT_LENGTH);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(salt);
        md.update(password.getBytes("UTF-8"));
        byte[] digest = md.digest();
        byte[] digestInDb = new byte[pwdInDb.length - SALT_LENGTH];
        System.arraycopy(pwdInDb, SALT_LENGTH, digestInDb, 0, digestInDb.length);
        if (Arrays.equals(digest, digestInDb))
            return true;
        return false;

    }

    private static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4 | HEX_NUMS_STR.indexOf(hexChars[pos + 1]));
        }
        return result;
    }

    private static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

}
