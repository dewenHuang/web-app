package com.huangdw.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @project: my-web-app
 * @description: 基类, 加载配置文件
 * @author: huangdw
 * @create: 2018-04-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml") // 参数只有一个, 可以省略{}
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class BaseJunit4Test extends AbstractTransactionalJUnit4SpringContextTests {// AbstractTransactionalJUnit4SpringContextTests类中有@Transactional注解
}
