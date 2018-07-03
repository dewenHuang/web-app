package com.huangdw.senior.interceptor;

import javax.servlet.http.HttpServletRequest;

/**
 * @project: web-app
 * @description: 用户中心默认登录校验器
 * @author: huangdw
 * @create: 2018-07-02 20:14
 */
public class UcenterDefaultLoginValidatorImpl implements LoginValidator {

    @Override
    public boolean validate(HttpServletRequest request) {
        return false;
    }
}
