package com.huangdw.dao;

import com.huangdw.entity.MenuEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper {
    int deleteById(Integer id);

    int insert(MenuEntity record);

    MenuEntity selectById(Integer id);

    int updateById(MenuEntity record);

    @Select("select * from t_menu")
    List<MenuEntity> selectAll();
}