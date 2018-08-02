package com.huangdw.aspect;

import com.huangdw.dto.CommonResult;
import com.huangdw.enums.XxxErrorEnum;
import com.huangdw.exception.CommonException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @program: my-controller-app
 * @description: Xxx异常处理切面
 * @author: huangdw
 * @create: 2018-04-13
 */
@Aspect
@Component
public class XxxExceptionAspect { // 只能拦截目标方法执行时发生的异常，不推荐使用

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(XxxExceptionAspect.class);

    @Around("execution(* com.huangdw.controller.*Controller.*(..))" +
            " && @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public Object around(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();

        try {
            // 前置通知, 在方法开始执行之前执行
            LOGGER.debug("The method {} begins with params {} ", methodName, Arrays.asList(joinPoint.getArgs()));
            // 执行目标方法
            Object result = joinPoint.proceed();
            // 返回通知, 在方法执行结束之后执行, 能访问目标方法的返回结果
            LOGGER.debug("The method {} ends with result {}", methodName, result);

            return result;
        } catch (Throwable throwable) {
            // 异常通知, 在方法抛出异常之后执行
//            LOGGER.error("The method {} with params {} occurs exception", methodName, Arrays.asList(joinPoint.getArgs()), throwable);
            if (throwable instanceof CommonException) {
                // 特定异常处理
                CommonException e = (CommonException) throwable;
                LOGGER.error("Catch biz exception, errorCode: {} errorMsg: {}", e.getError().getCode(), e.getErrorMsg(), e);
                return new CommonResult(e.getError());
            } else {
                LOGGER.error("Catch unknown exception", throwable);
                return new CommonResult(XxxErrorEnum.SYSTEM_ERROR);
            }
        } finally {
            // 后置通知, 在方法返回结果或者抛出异常之后都会执行, 不能访问目标方法的返回结果
            LOGGER.debug("The method {} ends, then need to release resources", methodName);
        }
    }
}
