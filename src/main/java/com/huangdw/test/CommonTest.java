package com.huangdw.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @project: web-app
 * @description: 通用测试类
 * @author: huangdw
 * @create: 2018-06-15 10:05
 */
public class CommonTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonTest.class);

//    public static final List<String> list = new ArrayList<>(Arrays.asList("ab", "cd", "ef"));

//    public static final List<String> list = new ArrayList<String>() {{
//        add("ab");
//        add("cd");
//        add("ef");
//    }};

    public static final List<String> list = new ArrayList<>();
    static {
        list.add("ab");
        list.add("cd");
        list.add("ef");
    }

    public static void main(String[] args) {
//        String s = "";
//        int i = Integer.parseInt(s);
//        System.out.println(i);

//        String ids = "1";
//        String[] idArr = ids.split(",");
//        for (String id : idArr) {
//            System.out.println(id);
//        }

//        String str = "";
//        String substring = str.substring(0, -1);
//        System.out.println(substring);

//        String idsWithSemi = ";1,2;"; // 第二个分号后面没有值, 可以忽略
//        String[] idsArr = idsWithSemi.split(";");
//        System.out.println(idsArr.length);
//        for (String ids : idsArr) {
//            System.out.println(ids);
//        }

        for (String s : list) {
            System.out.println(s);
        }

        LOGGER.info("列表大小: {}, 列表: {}.", list.size(), list);
    }
}
