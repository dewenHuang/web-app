package com.huangdw.test;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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

    public static void main(String[] args) throws UnsupportedEncodingException {
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

//        for (String s : list) {
//            System.out.println(s);
//        }
//
//        LOGGER.info("列表大小: {}, 列表: {}.", list.size(), list);

        String[] arr = list.toArray(new String[0]); // 或者 new String[list.size()]
        for (String s : arr) {
            System.out.println(s);
        }

        System.out.println(JSON.toJSONString(null));
        System.out.println(JSON.toJSONString(""));
        System.out.println(JSON.toJSONString(" "));

        long timeout = 3500L;
        int lockExpire = (int) (timeout / 1000);
        System.out.println(lockExpire);

        System.out.println("".equals(new StringBuffer().toString()));
        System.out.println("");

        System.out.println("".split("\r\n").length);
        System.out.println("".split("\r\n")[0]);

        String model = "vivo X9";
        System.out.println(URLEncoder.encode(model, "UTF-8"));
        System.out.println(URLDecoder.decode("vivo+X9", "UTF-8"));

        List<String> models = new ArrayList<>();
        models.add("vivo X21i A");
        System.out.println(models.contains("vivo X21i"));
    }
}
