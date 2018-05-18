package com.huangdw.Demo;

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
        Book book = DB.getAll().get(id);  //得到用户想买的书
        HttpSession session = request.getSession(false);
        List<Book> list = (List) session.getAttribute("list");  //得到用户用于保存所有书的容器
        if(list == null) {
            list = new ArrayList<Book>();
            session.setAttribute("list", list);
        }
        list.add(book);
        String url = request.getContextPath()+"/ListCartServlet";
        url = response.encodeRedirectURL(url); // 对sendRedirect方法后的url地址进行重写
        response.sendRedirect(url);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
