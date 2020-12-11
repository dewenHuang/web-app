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
     * 加密
     *
     * @param data 密码+盐=待加密字符串
     * @return
     */
    public static String encode(String data) {
        return DigestUtils.md5Hex(data);
    }

    private String encrypt(String password) {
        // 密码加盐（可选）
//        password = "abcdefg" + password + "hijklmn";

        try {
            // MD5 加密
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] digest = instance.digest(password.getBytes("UTF-8"));

            // 十六进制转换
            char[] chars = Hex.encodeHex(digest);
            return new String(chars);
            // 将上面两行合并
//            return Hex.encodeHexString(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        // 密码+盐（用户表中有盐字段）
//        String data = "admin" + "p6a7";
        String data = "123" + "3JaQ";
        System.out.println(Md5Util.encode(data));// 将加密后的字符串作为密码字段存到用户表中

        System.out.println(Md5Util.encode("sgfgsfd25426sgsgsfd"));
        System.out.println(new Md5Util().encrypt("sgfgsfd25426sgsgsfd"));
    }
}
