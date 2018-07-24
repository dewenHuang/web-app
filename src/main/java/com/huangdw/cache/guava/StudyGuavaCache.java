package com.huangdw.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @project: web-app
 * @description: 学习 Guava 缓存
 * @author: huangdw
 * @create: 2018-07-17 20:44
 */
public class StudyGuavaCache {
    public static void main(String[] args) throws InterruptedException {
        // 开始
//        Cache<String, String> cache = CacheBuilder.newBuilder().build();
//        cache.put("word", "Hello Guava Cache");
//        System.out.println(cache.getIfPresent("word"));

        // 设置最大存储
//        Cache<String, String> cache = CacheBuilder.newBuilder()
//                .maximumSize(2)
//                .build();
//        cache.put("key1", "value1");
//        cache.put("key2", "value2");
//        cache.put("key3", "value3");
//        System.out.println("第一个值: " + cache.getIfPresent("key1"));
//        System.out.println("第二个值: " + cache.getIfPresent("key2"));
//        System.out.println("第三个值: " + cache.getIfPresent("key3"));

        // 设置过期时间
//        // 1. expireAfterWrite 方法指定对象被写入到缓存后多久过期
//        Cache<String, String> cache = CacheBuilder.newBuilder()
//                .maximumSize(2)
//                .expireAfterWrite(3, TimeUnit.SECONDS)
//                .build();
//        cache.put("key1", "value1");
//        int second = 1;
//        while (true) {
//            Thread.sleep(1000);
//            System.out.println("写入 " + second++ + " 秒后取到 key1 的值为: " + cache.getIfPresent("key1"));
//        }

        // 2. expireAfterAccess 指定对象多久没有被访问后过期
//        Cache<String, String> cache = CacheBuilder.newBuilder()
//                .maximumSize(2)
//                .expireAfterAccess(3, TimeUnit.SECONDS)
//                .build();
//        cache.put("key1", "value1");
//        int second = 1;
//        while (true) {
//            String value1 = cache.getIfPresent("key1");
//            Thread.sleep(second * 1000);
//            System.out.println("访问 " + second++ + " 秒后取到 key1 的值为: " + cache.getIfPresent("key1"));
//        }

        // 显示清除
//        Cache<String, String> cache = CacheBuilder.newBuilder().build();
//        cache.put("key1", "value1");
//        cache.put("key2", "value2");
//        cache.put("key3", "value3");
//
//        List<String> list = new ArrayList<>();
//        list.add("key1");
//        list.add("key2");
//        cache.invalidateAll(list); // 批量清除 list 中全部 key 对应的记录
//
//        System.out.println("key1 -> " + cache.getIfPresent("key1"));
//        System.out.println("key2 -> " + cache.getIfPresent("key2"));
//        System.out.println("key3 -> " + cache.getIfPresent("key3"));

        // 移除监听器
//        RemovalListener<String, String> listener = new RemovalListener<String, String>() {
//            @Override
//            public void onRemoval(RemovalNotification<String, String> notification) {
//                System.out.println("[" + notification.getKey() + ":" + notification.getValue() + "] is removed!");
//            }
//        };
//        Cache<String, String> cache = CacheBuilder.newBuilder()
//                .maximumSize(3)
//                .removalListener(listener)
//                .build();
//        cache.put("key1", "value1");
//        cache.put("key2", "value2");
//        cache.put("key3", "value3");
//        cache.put("key4", "value4");
//        cache.put("key5", "value5");
//        cache.put("key6", "value6");
//        cache.put("key7", "value7");
//        cache.put("key8", "value8");

        // 统计信息
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .recordStats()
                .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value4");

        cache.getIfPresent("key1");
        cache.getIfPresent("key2");
        cache.getIfPresent("key3");
        cache.getIfPresent("key4");
        cache.getIfPresent("key5");
        cache.getIfPresent("key6");

        System.out.println(cache.stats());
    }
}
