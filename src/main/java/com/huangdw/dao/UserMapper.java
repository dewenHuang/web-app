package com.huangdw.dao;

import com.huangdw.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    /**
     * 查询用户购买的商品信息
     *
     * @return
     */
    List<User> selectUserAndItemsResultMap();
}