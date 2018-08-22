package com.huangdw.demo;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @project: web-app
 * @description: 获取用户上一次访问的时间
 * @author: huangdw
 * @create: 2018-05-18 10:35
 */
@WebServlet("/CookieDemo01")
public class CookieDemo01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 获取浏览器访问服务器时传递过来的cookie数组
        Cookie[] cookies = request.getCookies();
        // 获取两个Cookie字段
        Cookie usernameCookie = null;
        Cookie lastAccessTimeCookie = null;
        if (ArrayUtils.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), "username")) {
                    usernameCookie = cookie;
                }
                if (StringUtils.equals(cookie.getName(), "lastAccessTime")) {
                    lastAccessTimeCookie = cookie;
                }
            }
        }
        if (usernameCookie != null && lastAccessTimeCookie != null) {
            long lastAccessTime = Long.parseLong(lastAccessTimeCookie.getValue());
            Date date = new Date(lastAccessTime);

            out.write(URLDecoder.decode(usernameCookie.getValue(), "UTF-8") + ", 您上次访问的时间是: " + FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(date));
        } else {
            out.write("这是您第一次访问本站点!");
        }

        // 用户访问过之后重新设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
        Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis() + ""); // 创建一个cookie，cookie的名字是lastAccessTime
        // 设置Cookie的有效期为1天
        cookie.setMaxAge(60 * 60 * 24);
        // 将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
        response.addCookie(cookie);

        Cookie cookie1 = new Cookie("username", URLEncoder.encode("孤傲苍狼", "UTF-8"));
        // 设置Cookie的有效期为1天
        cookie1.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie1);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
