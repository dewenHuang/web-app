package com.huangdw.dao;

import com.huangdw.entity.MenuEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper {
    int deleteById(Integer id);

    int insert(MenuEntity record);

    MenuEntity selectById(Integer id);

    int updateById(MenuEntity record);

    /**
     * 查询所有菜单
     *
     * @return
     */
    @Select("select * from t_menu")
    List<MenuEntity> selectAll();

    /**
     * 查询根菜单
     *
     * @return
     */
    @Select("select * from t_menu where parent_id = 0")
    MenuEntity selectRootMenu();

    /**
     * 根据父菜单 ID 查询其子菜单集合
     *
     * @param id
     * @return
     */
    @Select("select * from t_menu where parent_id = #{id}")
    List<MenuEntity> queryChildMenus(Integer id);
}