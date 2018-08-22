package com.huangdw.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @project: web-app
 * @description:
 * @author: huangdw
 * @create: 2018-05-18 19:30
 */
@WebServlet("/ListCartServlet")
public class ListCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        List<BookEntity> list = (List) session.getAttribute("list");
        if(list == null || list.size() == 0) {
            out.write("对不起, 您还没有购买任何书籍!");
            return;
        }

        // 显示用户购买的书籍
        out.write("您购买了如下书籍:<br>");
        for (BookEntity book : list) {
            out.write(book.getName() + "<br>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
