package com.huangdw.test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @project: web-app
 * @description: 重载HystrixCommand 的getFallback方法实现逻辑
 * @author: huangdw
 * @create: 2018-10-17 17:48
 */
public class HelloWorldCommand2 extends HystrixCommand<String> {
    private String name;

    public HelloWorldCommand2(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
                /* 配置依赖超时时间,500毫秒*/
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        //sleep 1 秒,调用会超时
        Thread.sleep(1000);
        return "Hello " + name + ", thread: " + Thread.currentThread().getName();

        // 执行失败(抛出异常), 调用 getFallback 方法进行服务降级
//        throw new Exception();
    }

    @Override
    protected String getFallback() {
        return "execute Failed";
    }

    public static void main(String[] args) {
        HelloWorldCommand2 command = new HelloWorldCommand2("test-Fallback");
        String result = command.execute();// 同步执行
        System.out.println(result);
    }
}
