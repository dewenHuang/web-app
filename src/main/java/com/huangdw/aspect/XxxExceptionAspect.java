package com.huangdw.aspect;

import com.alibaba.fastjson.JSON;
import com.huangdw.dto.CommonResult;
import com.huangdw.enums.RespEnum;
import com.huangdw.exception.CustomException;
import com.huangdw.util.IpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: my-controller-app
 * @description: Xxx异常处理切面
 * @author: huangdw
 * @create: 2018-04-13
 */
@Order(1)// 值越小优先级越高, 默认最低优先级
@Aspect
@Component
public class XxxExceptionAspect {// 只能拦截目标方法执行时发生的异常，不推荐使用

    /**
     * 定义一个方法, 用于声明切入点表达式
     */
    @Pointcut("execution(* com.huangdw.controller.*Controller.*(..))" +
            " && @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void pointcut() {}

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(XxxExceptionAspect.class);

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 客户端IP
        String clientIp = IpUtil.getClientIp(request);
        // 请求URI
        String requestURI = request.getRequestURI();
        // 请求参数
        Map<String, Object> params = WebUtils.getParametersStartingWith(request, null);

        // 类名
        String typeName = pjp.getSignature().getDeclaringTypeName();
        // 方法名
        String methodName = pjp.getSignature().getName();

        try {
            // 前置通知, 在方法开始执行之前执行
            LOGGER.debug("The method {} begins with params {} ", typeName + "#" + methodName, JSON.toJSONString(params));// 打印方法开始日志
            // 执行目标方法
            Object result = pjp.proceed();
            // 返回通知, 在方法正常执行之后执行, 能访问目标方法的返回结果
            LOGGER.debug("The method {} ends with result {}", typeName + "#" + methodName, JSON.toJSONString(result));// 如果方法抛出异常也要打印结束日志，可以放在finally中打印
            // 返回执行结果
            return result;
        } catch (Throwable throwable) {
            // 异常通知, 在方法抛出异常之后执行, 一般在此处返回通用结果Dto
//            LOGGER.error("The method {} with params {} occurs exception", methodName, Arrays.asList(pjp.getArgs()), throwable);
            if (throwable instanceof CustomException) {
                // 特定异常处理
                CustomException e = (CustomException) throwable;
                LOGGER.error("Catch biz exception, errorCode: {} errorMsg: {}", e.getError().getCode(), e.getError().getMsg(), e);
                return CommonResult.fail(e.getError());
            } else {
                // 未知异常处理
                LOGGER.error("Catch unknown exception", throwable);
                return CommonResult.fail(RespEnum.SERVER_FAIL);
            }
        } finally {
            // 后置通知, 在方法正常执行或者抛出异常之后都会执行, 不能访问目标方法的返回结果, 一般用于释放资源/打印超时请求等
            LOGGER.debug("The method {} ends, then need to release resources", typeName + "#" + methodName);
        }
    }
}
