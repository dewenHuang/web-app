package com.huangdw.service.impl;

import com.huangdw.dao.TeleCardOrderMapper;
import com.huangdw.entity.TeleCardOrder;
import com.huangdw.service.TeleCardOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @project: web-app
 * @description:
 * @author: huangdw
 * @create: 2018-11-06 15:48
 */
@Service
public class TeleCardOrderServiceImpl implements TeleCardOrderService {
    @Autowired
    private TeleCardOrderMapper teleCardOrderMapper;

    @Override
    public TeleCardOrder queryById(long id) {
        return teleCardOrderMapper.selectById(id);
    }
}
