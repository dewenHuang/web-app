package com.huangdw.test;

import com.huangdw.dao.TeleCardOrderMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @project: web-app
 * @description: DAO层测试类
 * @author: huangdw
 * @create: 2018-05-14 15:49
 */
public class DaoTest extends BaseJunit4Test {

    @Autowired
    private TeleCardOrderMapper teleCardOrderMapper;

    @Test
    public void testDao() {
        System.out.println(teleCardOrderMapper.selectByPrimaryKey(28L));
    }
}
