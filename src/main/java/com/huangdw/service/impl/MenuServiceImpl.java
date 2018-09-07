package com.huangdw.service.impl;

import com.huangdw.dao.MenuMapper;
import com.huangdw.entity.MenuEntity;
import com.huangdw.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @project: web-app
 * @description: 菜单服务实现
 * @author: huangdw
 * @create: 2018-09-07 09:43
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuEntity> queryAll() {
        return menuMapper.selectAll();
    }
}
