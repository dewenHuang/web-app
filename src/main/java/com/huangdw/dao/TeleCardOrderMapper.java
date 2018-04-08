package com.huangdw.dao;

import com.huangdw.entity.TeleCardOrder;
import java.util.List;

public interface TeleCardOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TeleCardOrder record);

    TeleCardOrder selectByPrimaryKey(Long id);

    List<TeleCardOrder> selectAll();

    int updateByPrimaryKey(TeleCardOrder record);
}