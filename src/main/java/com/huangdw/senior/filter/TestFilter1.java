package com.huangdw.senior.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @project: web-app
 * @description:
 * @author: huangdw
 * @create: 2018-07-04 17:06
 */
public class TestFilter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TestFilter1 init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 在请求服务器之后执行, 且在 DispatcherServlet(前端控制器, doService()->doDispatch()) 之前执行
        System.out.println("############# TestFilter1 doFilter before #############");
        filterChain.doFilter(servletRequest, servletResponse);
        // 在响应浏览器之前执行, 且在 Interceptor(afterCompletion()) 之后执行
        System.out.println("############# TestFilter1 doFilter after #############");
    }

    @Override
    public void destroy() {
        System.out.println("TestFilter1 destroy...");
    }
}
