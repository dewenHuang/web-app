package com.huangdw.dao;

import com.huangdw.entity.Customer;

public interface CustomerMapper {
    int deleteById(Integer id);

    int insert(Customer record);

    Customer selectById(Integer id);

    int updateById(Customer record);

    /**
     * 根据名称查询顾客信息(包含车票信息)
     *
     * @param name
     * @return
     */
    Customer selectCustomerByName(String name);
}