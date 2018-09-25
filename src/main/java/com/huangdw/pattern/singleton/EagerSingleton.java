package com.huangdw.pattern.singleton;

/**
 * @project: web-app
 * @description: 单例之饿汉式, 线程安全的
 * @author: huangdw
 * @create: 2018-09-25 11:41
 */
public class EagerSingleton {
    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return instance;
    }
}
