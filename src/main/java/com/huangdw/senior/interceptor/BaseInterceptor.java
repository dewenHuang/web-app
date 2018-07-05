package com.huangdw.senior.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @project: web-app
 * @description:
 * @author: huangdw
 * @create: 2018-07-04 17:16
 */
public class BaseInterceptor implements HandlerInterceptor {
    /**
     * 在 DispatcherServlet 之后执行, 且在 Controller 之前执行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("============= BaseInterceptor preHandle executed =============");
        return true;
    }

    /**
     * 在 Controller/Handler(后端控制器/处理器) 之后执行, 且在视图渲染之前执行
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("============= BaseInterceptor postHandle executed =============");
    }

    /**
     * 在视图渲染之后执行, 返回 Filter 执行 chain.doFilter() 之后的代码, 最后响应浏览器.
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("============= BaseInterceptor afterCompletion executed =============");
    }
}
