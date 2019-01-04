package com.huangdw.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;

/**
 * MD5 工具类
 *
 * @author huangdw
 * @date 2019/1/3
 */
public class Md5Util {
    /**
     *
     * 加密
     *
     * @param data 密码+盐
     * @return
     */
    public static String encode(String data) {
        return DigestUtils.md5Hex(data);
    }

    private String encrypt(String password) {
        // 密码加盐（可选）
//        password = "abcdefg" + password + "hijklmn";

        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            // MD5 加密
            byte[] digest = instance.digest(password.getBytes());
            // 十六进制转换
            char[] chars = Hex.encodeHex(digest);

            return new String(chars);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
