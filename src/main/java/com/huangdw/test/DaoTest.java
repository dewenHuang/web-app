package com.huangdw.test;

import com.huangdw.dao.TeleCardOrderMapper;
import com.huangdw.entity.TeleCardOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @project: web-app
 * @description: DAO层测试类
 * @author: huangdw
 * @create: 2018-05-14 15:49
 */
public class DaoTest extends BaseJunit4Test {// 继承父类(defaultRollback = true), 确保对数据库的操作(增\删\改)无论是否发生异常均回滚

    @Autowired
    private TeleCardOrderMapper teleCardOrderMapper;

    @Test
    public void testDao() {
        System.out.println(teleCardOrderMapper.selectById(28L));
    }

    @Test
    public void testRollback() {
        TeleCardOrder record = new TeleCardOrder();
        record.setId(28L);
        record.setOrderNo("TCARDac16d99715217201357300hdw");
        teleCardOrderMapper.updateById(record);
//        throw new RuntimeException();
    }
}
