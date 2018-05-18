package com.huangdw.Demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @project: web-app
 * @description: 首页：列出所有书
 * @author: huangdw
 * @create: 2018-05-18 18:27
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 创建Session
        request.getSession();
        out.write("本网站有如下书：<br>");
        Map<String, Book> allBooks = DB.getAll();
        for (Map.Entry<String, Book> entry : allBooks.entrySet()) {
            Book book = entry.getValue();
            String url = request.getContextPath() + "/BuyServlet?id=" + book.getId();
            url = response.encodeURL(url); // 对超链接的url地址进行重写
            out.write(book.getName() + " <a href='" + url + "'>购买</a><br>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
