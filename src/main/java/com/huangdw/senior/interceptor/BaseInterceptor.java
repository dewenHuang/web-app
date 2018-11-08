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
     * <p>可以考虑做权限, 日志, 事务等</p>
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
        return true;// 若返回 false, 则不会再调用后续的拦截器和目标方法
    }

    /**
     * 在 Controller/Handler(后端控制器/处理器) 之后执行, 且在视图渲染之前执行
     * <p>可以对请求域中的属性或试图做出修改</p>
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
     * <p>可以用来释放资源</p>
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
