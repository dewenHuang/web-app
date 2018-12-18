package com.huangdw.aspect;

import com.alibaba.fastjson.JSON;
import com.huangdw.utils.IpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 拦截Controller接口, 打印超时(200毫秒)的请求
 *
 * @author huangdw
 * @date 2018/12/11
 */
@Aspect
@Component
public class TimeoutAspect {
    // 日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeoutAspect.class);

    @Around("execution(* com.huangdw.controller..*Controller.*(..))" +
            " && @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {// 异常抛出由专门的异常解析器或异常切面进行捕获处理
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        StopWatch stopWatch = new StopWatch();

        // 客户端IP
        String clientIp = IpUtil.getClientIp(request);
        // 服务器端IP
        String serverIp = IpUtil.getServerIp();
        // 请求URI
        String uri = request.getRequestURI();
        // 请求参数
        Map<String, Object> parameters = WebUtils.getParametersStartingWith(request, null);
        // 返回结果
        Object result = null;
        try {
            // 前置通知
            stopWatch.start();
            result = joinPoint.proceed();
            stopWatch.stop();
            // 返回通知, 可以访问返回结果result
        } finally {
            // 后置通知, 无论是否发生异常都会执行
            if (stopWatch.getTotalTimeMillis() > 200) {
                LOGGER.warn("An handler executes timeout, clientIp: {}, serverIp: {}, uri: {}, elapsedTime: {}ms, params: {}, result: {}",
                        clientIp, serverIp, uri, stopWatch.getTotalTimeMillis(), parameters, JSON.toJSONString(result));
            }
        }
        return result;
    }
}
