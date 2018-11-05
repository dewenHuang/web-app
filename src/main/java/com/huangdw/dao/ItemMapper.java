package com.huangdw.dao;

import com.huangdw.entity.Item;

public interface ItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Item record);
}