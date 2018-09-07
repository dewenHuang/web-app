package com.huangdw.service;

import com.huangdw.entity.MenuEntity;

import java.util.List;

/**
 * @project: web-app
 * @description: 菜单服务接口
 * @author: huangdw
 * @create: 2018-09-07 09:42
 */
public interface MenuService {
    /**
     * 查询所有的菜单数据
     *
     * @return
     */
    List<MenuEntity> queryAll();
}
