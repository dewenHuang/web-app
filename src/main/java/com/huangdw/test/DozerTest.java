package com.huangdw.test;

import org.dozer.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @project: my-web-app
 * @description: Dozer测试类
 * @author: huangdw
 * @create: 2018-04-20
 */
public class DozerTest extends BaseJunit4Test {

    @Autowired
    private Mapper beanMapper;

    @Test
    public void testMapping() {
        // 使用Dozer测试JavaBean属性拷贝
    }
}
