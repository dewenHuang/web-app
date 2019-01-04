package com.huangdw.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * @program: my-controller-app
 * @description: Spring上下文工具类
 * @author: huangdw
 * @create: 2018-04-13
 */
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getContext() {// 这不是一个常规的 Getter 方法
        checkApplicationContext();
        return applicationContext;
    }

    /**
     * 通过name获取Bean
     *
     * @param name
     * @return
     * @throws BeansException
     */
    public static Object getBean(String name) {// 手动对返回值进行强转
        checkApplicationContext();
        return applicationContext.getBean(name);
    }

    /**
     * 通过clazz获取Bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(clazz);
    }

    /**
     * 通过name和clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(name, clazz);
    }

    private static void checkApplicationContext() {
        Assert.notNull(applicationContext,
                "applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtil");
    }
}
