package com.github.tj123.frame.service.common;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class PasswordUtil {

    /**
     * 加密因子长度
     */
    private static final Integer SALT_LENGTH = 12;

    /**
     * 对密码加密
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
        return byte2String(pwd);
    }

    /**
     * 判断密码是否匹配
     */
    public static boolean isMatch(String password, String dbPassword) throws Exception {
        byte[] pwdInDb = string2Byte(dbPassword);
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

    private static byte[] string2Byte(String str) {
        return Base64.getDecoder().decode(str);
    }

    private static String byte2String(byte[] bt) {
        return Base64.getEncoder().encodeToString(bt).replaceAll("=", "");
    }

}
