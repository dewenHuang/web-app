<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/4
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FilterDemo</title>
</head>
<body>
    <%
        // 在 Interceptor(postHandler()) 之后执行, 且在 Interceptor(afterCompletion()) 之前执行
        System.out.println("+++++++++++++ test.jsp is rendering +++++++++++++");
    %>
    <div align="center">
        This is test page.
    </div>
</body>
</html>
