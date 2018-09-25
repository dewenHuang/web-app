package com.huangdw.pattern.singleton;

/**
 * @project: web-app
 * @description: 单例之懒汉式, 线程不安全
 * @author: huangdw
 * @create: 2018-09-25 11:00
 */
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (null == instance) {// 双重检查锁, 解决运行效率问题
            synchronized (LazySingleton.class) {// 方案二: 同步代码块, 解决线程安全问题
                if (null == instance) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
