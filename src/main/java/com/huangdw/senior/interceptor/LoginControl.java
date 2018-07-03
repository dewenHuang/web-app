package com.huangdw.senior.interceptor;

import java.lang.annotation.*;

/**
 * @project: web-app
 * @description: 登录访问控制
 * @author: huangdw
 * @create: 2018-07-02 20:23
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginControl {

    // 是否需要校验登录
    RequiredEnum required() default RequiredEnum.YES;
    // 返回类型
    ResultTypeEnum resultType() default ResultTypeEnum.FORWARD;
    // 登录校验器
    Class<? extends LoginValidator> validatorClass() default UcenterDefaultLoginValidatorImpl.class;
    // 是否需要 HTTPS 登录
    RequiredEnum needHttps() default RequiredEnum.NO;
}
