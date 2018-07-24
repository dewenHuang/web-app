package com.huangdw.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @project: web-app
 * @description: 自动加载
 * @author: huangdw
 * @create: 2018-07-03 14:58
 */
public class Autoloading {

    private static Cache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(3)
            .build();

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            public void run() {
                System.out.println("thread1 begin");
                String value = "";
                try {
                    value = cache.get("key", new Callable<String>() {
                        public String call() throws Exception {
                            System.out.println("thread1 loading..."); //加载数据线程执行标志
                            Thread.sleep(1000); //模拟加载时间
                            return "value1";
                        }
                    });
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 end, key:" + value);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println("thread2 begin");
                String value = "";
                try {
                    value = cache.get("key", new Callable<String>() {
                        public String call() throws Exception {
                            System.out.println("thread2 loading..."); //加载数据线程执行标志
                            Thread.sleep(1000); //模拟加载时间
                            return "value2";
                        }
                    });
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 end, key:" + value);
            }
        }).start();

    }
}
