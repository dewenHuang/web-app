package com.huangdw.demo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @project: web-app
 * @description: 模拟数据库
 * @author: huangdw
 * @create: 2018-05-18 18:33
 */
public class DB {

    private static Map<String, BookEntity> map = new LinkedHashMap<>(); // 有序的 HashMap

    static {
        map.put("1", new BookEntity(1, "JavaWeb开发"));
        map.put("2", new BookEntity(2, "Spring开发"));
        map.put("3", new BookEntity(3, "Hibernate开发"));
        map.put("4", new BookEntity(4, "Struts开发"));
        map.put("5", new BookEntity(5, "Ajax开发"));
    }

    /**
     * 获取所有书籍
     *
     * @return
     */
    public static Map<String, BookEntity> getAllBooks() {
        return map;
    }
}
