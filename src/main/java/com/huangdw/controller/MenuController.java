package com.huangdw.controller;

import com.huangdw.entity.MenuEntity;
import com.huangdw.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project: web-app
 * @description: 菜单控制器
 * @author: huangdw
 * @create: 2018-09-07 09:41
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/loadData")
    @ResponseBody
    public Object loadData() {
        List<MenuEntity> menus = new ArrayList<>();

        // 方案一: 手动拼接
//        MenuEntity rootMenu = menuService.queryRootMenu();// 虚拟一个根菜单
//
//        List<MenuEntity> childMenus = menuService.queryChildMenus(rootMenu.getId());// 根菜单作为父菜单
//        rootMenu.setChildren(childMenus);// 组合根菜单与其子菜单的关系
//        for (MenuEntity  childMenu : childMenus) {
//            List<MenuEntity> childChildMenus = menuService.queryChildMenus(childMenu.getId());// 根菜单的子菜单作为父菜单
//            childMenu.setChildren(childChildMenus);// 组合根菜单的子菜单与其子菜单的关系
//        }
//
//        menus.add(rootMenu);

        // 方案二: 递归调用
//        MenuEntity parentMenu = new MenuEntity();
//        parentMenu.setId(0);
//        queryChildMenus(parentMenu);
//
//        return parentMenu.getChildren();

        // 方案三: 嵌套 for 循环
//        List<MenuEntity> ms = menuService.queryAll();
//        for (MenuEntity m : ms) {
//            if (m.getParentId() == 0) {// m 是惟一的根节点, 也是父节点
//                menus.add(m);
//            } else {// m 既是子节点, 又是父节点
//                for (MenuEntity innerMenu : ms) {
//                    if (m.getParentId().equals(innerMenu.getId())) {// 把 m 看做子节点, 找到其父节点并建立父子关系
//                        innerMenu.getChildren().add(m);
//                        break;
//                    }
//                }
//            }
//        }

        // 方案四: Map 集合方式, 避免嵌套 for 循环
        List<MenuEntity> ms = menuService.queryAll();
        Map<Integer, MenuEntity> menuMap = new HashMap<>();
        for (MenuEntity m : ms) {
            menuMap.put(m.getId(), m);
        }
        for (MenuEntity m : ms) {
            if (m.getParentId() == 0) {
                menus.add(m);
            } else {
                menuMap.get(m.getParentId()).getChildren().add(m);
            }
        }

        return menus;
    }

    /**
     * 递归查询菜单信息
     *
     * @param parentMenu
     */
    private void queryChildMenus(MenuEntity parentMenu) {
        List<MenuEntity> childMenus = menuService.queryChildMenus(parentMenu.getId());
        parentMenu.setChildren(childMenus);
        for (MenuEntity childMenu : childMenus) {
            queryChildMenus(childMenu);
        }
    }
}
