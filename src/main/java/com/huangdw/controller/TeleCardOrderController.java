package com.huangdw.controller;

import com.huangdw.service.TeleCardOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @project: web-app
 * @description:
 * @author: huangdw
 * @create: 2018-11-06 15:51
 */
@Controller
public class TeleCardOrderController {
    @Autowired
    private TeleCardOrderService teleCardOrderService;

    @RequestMapping("/teleCardOrder/queryById")
    @ResponseBody
    public Object queryById(Long id) {
        return teleCardOrderService.queryById(id);
    }
}
