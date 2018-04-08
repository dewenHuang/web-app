package com.huangdw.dao;

import com.huangdw.entity.TeleCardOrder;

public interface TeleCardOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TeleCardOrder record);

    int insertSelective(TeleCardOrder record);

    TeleCardOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeleCardOrder record);

    int updateByPrimaryKey(TeleCardOrder record);
}