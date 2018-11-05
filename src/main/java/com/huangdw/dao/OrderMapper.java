package com.huangdw.dao;

import com.huangdw.entity.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);
}