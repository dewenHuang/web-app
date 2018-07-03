package com.huangdw.cache.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

/**
 * @project: web-app
 * @description: 当从LoadingCache中读取一个指定key的记录时，如果该记录不存在，则LoadingCache可以自动执行加载数据到缓存的操作。
 * @author: huangdw
 * @create: 2018-07-03 15:17
 */
public class LoadingCacheDemo {

    public static void main(String[] args) throws ExecutionException {
        CacheLoader<String, String> loader = new CacheLoader<String, String> () {
            public String load(String key) throws Exception {
                Thread.sleep(1000); //休眠1s，模拟加载数据
                System.out.println(key + " is loaded from a cacheLoader!");
                return key + "'s value";
            }
        };

        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .build(loader); //在构建时指定自动加载器

        System.out.println(loadingCache.get("key1"));
        System.out.println(loadingCache.get("key2"));
        System.out.println(loadingCache.get("key3"));
        System.out.println(loadingCache.get("key1")); // key1 记录存在, 不需要执行 load 方法(注意上面的 maximumSize >= 3)
    }
}
