package com.huangdw.senior.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @project: web-app
 * @description: 用户工具类
 * @author: huangdw
 * @create: 2018-07-02 21:20
 */
public class UserUtil {

    public static long getIdFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        long userId = (long) session.getAttribute(Constants.SESSION_KEY_USER_ID);
        return userId;
    }
}
