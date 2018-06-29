package com.huangdw.demo;

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
 * @description: 首页: 列出所有书
 * @author: huangdw
 * @create: 2018-05-18 18:27
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 手动创建 Session(如果访问的是JSP页面, 那么服务器会自动创建 Session)
        request.getSession();
        out.write("本网站有如下书籍：<br>");
        Map<String, BookEntity> allBooks = DB.getAllBooks();
        for (Map.Entry<String, BookEntity> entry : allBooks.entrySet()) {
            BookEntity book = entry.getValue();
            String url = request.getContextPath() + "/BuyServlet?id=" + book.getId(); // request.getContextPath()等于/, 可以省略
            url = response.encodeURL(url); // 用于对表单 action 和超链接的 url 地址进行重写; 如果浏览器没有禁用 Cookie, 这行代码就不会生效.
            out.write(book.getName() + " <a href='" + url + "'>购买</a><br>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
