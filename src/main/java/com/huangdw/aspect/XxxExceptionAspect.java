package com.huangdw.aspect;

import com.huangdw.dto.CommonResult;
import com.huangdw.enums.XxxErrorEnum;
import com.huangdw.exception.CustomException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @program: my-controller-app
 * @description: Xxx异常处理切面
 * @author: huangdw
 * @create: 2018-04-13
 */
@Order(1)// 值越小优先级越高, 默认最低优先级
@Aspect
@Component
public class XxxExceptionAspect { // 只能拦截目标方法执行时发生的异常，不推荐使用

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
        String methodName = pjp.getSignature().getName();

        try {
            // 前置通知, 在方法开始执行之前执行
            LOGGER.debug("The method {} begins with params {} ", methodName, Arrays.asList(pjp.getArgs()));
            // 执行目标方法
            Object result = pjp.proceed();
            // 返回通知, 在方法正常执行之后执行, 能访问目标方法的返回结果
            LOGGER.debug("The method {} ends with result {}", methodName, result);
            // 返回执行结果
            return result;
        } catch (Throwable throwable) {
            // 异常通知, 在方法抛出异常之后执行, 一般在此处返回通用结果Dto
//            LOGGER.error("The method {} with params {} occurs exception", methodName, Arrays.asList(pjp.getArgs()), throwable);
            if (throwable instanceof CustomException) {
                // 特定异常处理
                CustomException e = (CustomException) throwable;
                LOGGER.error("Catch biz exception, errorCode: {} errorMsg: {}", e.getError().getCode(), e.getError().getMsg(), e);
                return new CommonResult(e.getError());
            } else {
                // 未知异常处理
                LOGGER.error("Catch unknown exception", throwable);
                return new CommonResult(XxxErrorEnum.SYSTEM_ERROR);
            }
        } finally {
            // 后置通知, 在方法正常执行或者抛出异常之后都会执行, 不能访问目标方法的返回结果, 一般用于释放资源/打印超时请求等
            LOGGER.debug("The method {} ends, then need to release resources", methodName);
        }
    }
}
