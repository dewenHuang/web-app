package com.huangdw.service;

import com.huangdw.entity.TeleCardOrder;

/**
 * @project: web-app
 * @description:
 * @author: huangdw
 * @create: 2018-11-06 15:47
 */
public interface TeleCardOrderService {
    /**
     * 根据 ID 查询电信号卡订单
     *
     * @param id
     * @return
     */
    TeleCardOrder queryById(long id);
}
