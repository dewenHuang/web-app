package com.huangdw.controller;

import com.huangdw.senior.interceptor.LoginControl;
import com.huangdw.senior.interceptor.QsurveyLoginValidatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @project: web-app
 * @description: 用于测试登录校验注解的处理器
 * @author: huangdw
 * @create: 2018-07-03 16:57
 */
@Controller
@LoginControl(validatorClass = QsurveyLoginValidatorImpl.class)
public class UserSurveyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserSurveyController.class);
    // ...

    @RequestMapping(value = "/")
    @LoginControl(validatorClass = QsurveyLoginValidatorImpl.class)
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("index");
        // ...

        return modelAndView;
    }
}
