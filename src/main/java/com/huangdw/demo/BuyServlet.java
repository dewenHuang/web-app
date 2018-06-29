package com.huangdw.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @project: web-app
 * @description:
 * @author: huangdw
 * @create: 2018-05-18 19:21
 */
@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        BookEntity book = DB.getAllBooks().get(id); // 得到用户购买的书籍

        HttpSession session = request.getSession(false);
        List<BookEntity> list = (List) session.getAttribute("list"); // 得到用于保存所有用户购买的书籍的容器
        if(list == null) {
            list = new ArrayList<>();
        }
        list.add(book); // 往容器中添加书籍
        session.setAttribute("list", list); // 更新 Session 中的书籍容器

        String url = request.getContextPath() + "/ListCartServlet"; // request.getContextPath()等于/, 可以省略
        url = response.encodeRedirectURL(url); // 用于对 sendRedirect 方法后的url地址进行重写; 如果浏览器没有禁用 Cookie, 这行代码就不会生效.
        response.sendRedirect(url);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
