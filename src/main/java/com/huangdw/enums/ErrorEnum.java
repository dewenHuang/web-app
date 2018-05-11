package com.huangdw.enums;

/**
 * @project: web-app
 * @description: 错误枚举（各个业务异常枚举都要实现它）
 * @author: huangdw
 * @create: 2018-05-11 15:25
 */
public interface ErrorEnum {

    int getCode();

    String getMsg();
}
