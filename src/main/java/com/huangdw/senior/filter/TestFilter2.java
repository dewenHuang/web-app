package com.huangdw.senior.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @project: web-app
 * @description:
 * @author: huangdw
 * @create: 2018-07-04 17:10
 */
public class TestFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TestFilter2 init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("############# TestFilter2 doFilter before #############");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("############# TestFilter2 doFilter after #############");
    }

    @Override
    public void destroy() {
        System.out.println("TestFilter2 destroy...");
    }
}
