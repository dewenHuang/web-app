package com.huangdw.pattern.singleton;

/**
 * @project: web-app
 * @description: 单例之饿汉式, 线程安全的, 因为在类加载初始化时就创建好一个静态的对象供外部使用
 * @author: huangdw
 * @create: 2018-09-25 11:41
 */
public class EagerSingleton {
    private static EagerSingleton instance = new EagerSingleton();

    /**
     * 私有的构造方法, 避免类在外部被实例化
     */
    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return instance;
    }
}
