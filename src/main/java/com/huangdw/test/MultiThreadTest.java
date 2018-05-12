package com.huangdw.test;

/**
 * @project: web-app
 * @description: 多线程测试类
 * @author: huangdw
 * @create: 2018-05-12 15:06
 */
public class MultiThreadTest {

    public static void main(String[] args) {
        new Thread("菩提树下的杨过") {
            @Override
            public void run() {
                System.out.println("1.卧枝商恨低");
            }
        }.start();

        new Thread("天空中的飞鸟") {
            @Override
            public void run() {
                System.out.println("2.卧梅又闻花");
            }
        }.start();

        System.out.println("3.要问卧似水");
        System.out.println("4.倚头答春绿");
    }
}
