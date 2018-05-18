package com.huangdw.Demo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @project: web-app
 * @description: 模拟数据库
 * @author: huangdw
 * @create: 2018-05-18 18:33
 */
public class DB {

    private static Map<String, Book> map = new LinkedHashMap<>();

    static {
        map.put("1", new Book(1, "JavaWeb开发"));
        map.put("2", new Book(2, "Spring开发"));
        map.put("3", new Book(3, "Hibernate开发"));
        map.put("4", new Book(4, "Struts开发"));
        map.put("5", new Book(5, "Ajax开发"));
    }

    public static Map<String, Book> getAll() {
        return map;
    }
}
