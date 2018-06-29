package com.huangdw.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @project: web-app
 * @description: 删除cookie
 * @author: huangdw
 * @create: 2018-05-18 14:35
 */
@WebServlet("/CookieDemo02")
public class CookieDemo02 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建一个名字为lastAccessTime的cookie
        Cookie cookie = new Cookie("lastAccessTime", null);
        // 将cookie的有效期设置为0，命令浏览器删除该cookie
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        Cookie cookie1 = new Cookie("username", "");
        cookie1.setMaxAge(0);
        response.addCookie(cookie1);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write("删除cookie成功！");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
