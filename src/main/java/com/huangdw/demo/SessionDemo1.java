package com.huangdw.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @project: web-app
 * @description:
 * @author: huangdw
 * @create: 2018-05-18 17:55
 */
@WebServlet("/SessionDemo1")
public class SessionDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 使用request对象的getSession()获取session，如果session不存在则创建一个
        HttpSession session = request.getSession(); // 等价于 request.getSession(true), 这样在往 Session 中存数据时才不会出现空指针异常
        // 将数据存储到session中
        session.setAttribute("data", "孤傲苍狼");
        // 获取session的Id
        String sessionId = session.getId();
        // 判断session是不是新创建的
        if (session.isNew()) {
            out.write("新建了一个Session，ID为" + sessionId);
        } else {
            out.write("获取已经存在的Session，ID为" + sessionId);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
