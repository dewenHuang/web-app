package com.huangdw.controller;

import com.huangdw.service.AccessLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @RequestMapping(value = "/access")
    public void access(String username, HttpServletResponse response) throws IOException {
        System.out.println("后台接收到的中文参数是：" + username);
//        response.setContentType("text/html;charset=UTF-8");

        //尝试获取令牌
        if (accessLimitService.tryAcquire()) {
            //模拟业务执行500毫秒
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            response.getWriter().println("访问成功 [" + Calendar.getInstance().getTimeInMillis() + "]");
        } else {
            response.getWriter().println("访问失败 [" + Calendar.getInstance().getTimeInMillis() + "]");
        }
    }
}
