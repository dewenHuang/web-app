package com.huangdw.controller;

import com.huangdw.service.AccessLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;

/**
 * @project: web-app
 * @description:
 * @author: huangdw
 * @create: 2018-06-01 15:24
 */
@Controller
public class HelloController {

    @Autowired
    private AccessLimitService accessLimitService;

    @RequestMapping("/access")
    @ResponseBody
    public String access() {
        //尝试获取令牌
        if (accessLimitService.tryAcquire()) {
            //模拟业务执行500毫秒
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "aceess success [" + Calendar.getInstance().getTimeInMillis() + "]";
        } else {
            return "aceess limit [" + Calendar.getInstance().getTimeInMillis() + "]";
        }
    }
}
