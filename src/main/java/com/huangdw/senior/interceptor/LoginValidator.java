package com.huangdw.senior.interceptor;

import javax.servlet.http.HttpServletRequest;

/**
 * @project: web-app
 * @description: 登录校验器
 * @author: huangdw
 * @create: 2018-07-02 20:12
 */
public interface LoginValidator {

    /**
     * 校验登录
     *
     * @param request
     * @return
     */
    boolean validate(HttpServletRequest request);
}
