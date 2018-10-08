package com.huangdw.pattern.singleton;

/**
 * @project: web-app
 * @description: 单例之懒汉式, 线程不安全
 * @author: huangdw
 * @create: 2018-09-25 11:00
 */
public class LazySingleton {
    // volatile 关键字能禁止指令重排序, 避免出现 1-3-2 的指令执行顺序, 因为当执行完 3 后, instance 不为 null 但没有完全初始化, 此时切换线程就返回这个没有完全初始化对象
    private volatile static LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {// 双重检查锁, 解决运行效率问题
            synchronized (LazySingleton.class) {// 方案二: 同步代码块, 解决线程安全问题
                if (instance == null) {
                    instance = new LazySingleton();// 非原子操作: 1.为 instance 分配内存 2.调用 LazySingleton 的构造函数来初始化成员变量 3.将 instance 对象指向分配的内存空间(执行完这步 instance 就为非 null 了)
                }
            }
        }
        return instance;
    }
}
