package com.huangdw.senior.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @project: web-app
 * @description:
 * @author: huangdw
 * @create: 2018-07-04 17:06
 */
public class TestFilter1 extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 在请求服务器之后执行, 且在 DispatcherServlet(前端控制器, doService()->doDispatch()) 之前执行
        System.out.println("############# TestFilter1 doFilter before #############");
        filterChain.doFilter(request, response);
        // 在响应浏览器之前执行, 且在 Interceptor(afterCompletion()) 之后执行
        System.out.println("############# TestFilter1 doFilter after #############");
    }
}
