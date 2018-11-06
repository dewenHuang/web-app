package com.huangdw.dao;

import com.huangdw.entity.TeleCardOrder;

public interface TeleCardOrderMapper {
    int deleteById(Long id);

    int insert(TeleCardOrder record);

    TeleCardOrder selectById(Long id);

    int updateById(TeleCardOrder record);
}