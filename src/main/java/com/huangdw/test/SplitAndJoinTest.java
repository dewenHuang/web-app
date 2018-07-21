package com.huangdw.test;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @project: web-app
 * @description: 分割和连接测试类
 * @author: huangdw
 * @create: 2018-07-19 16:34
 */
public class SplitAndJoinTest {
    public static void main(String[] args) {
        // 1. 分割字符串
        String str = "a,b,c";
        List<String> list = Arrays.asList(str.split(","));
        System.out.println(list.get(0) + "|" + list.get(1) + "|" + list.get(2));

        String str2 = "a, b, c";
        List<String> list2 = Splitter.on(",").trimResults().splitToList(str); // 同时处理了空格
        System.out.println(list2.get(0) + "|" + list2.get(1) + "|" + list2.get(2));

        // 2. 连接列表
        List<String> list3 = new ArrayList<>();
        list3.add("d");
        list3.add(" e");
        list3.add(" f");
        StringBuffer sb = new StringBuffer();
        for (String s : list3) {
            sb.append(s.trim()).append(","); // 同时处理了空格
        }
        String str3 = sb.toString();
        str3 = str3.substring(0, str3.length() - 1);
        System.out.println(str3);

        String str4 = Joiner.on(",").join(list3);
        System.out.println(str4);
    }
}
