package com.huangdw.exception;

import com.huangdw.enums.RespEnum;

/**
 * @program: my-controller-app
 * @description: 自定义业务异常类
 * @author: huangdw
 * @create: 2018-04-13
 */
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 5816725436661829830L;

    /** 错误枚举 */
    private RespEnum error;

    public CustomException(RespEnum error) {
        super(error.getMsg());
        this.error = error;
    }

    public CustomException(String message, RespEnum error) {
        super(message);
        this.error = error;
    }

    public CustomException(Throwable cause, RespEnum error) {
        super(cause);
        this.error = error;
    }

    public CustomException(String message, Throwable cause, RespEnum error) {
        super(message, cause);
        this.error = error;
    }

    public RespEnum getError() {
        return error;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {// 重写 fillInStackTrace 方法，不打印调用信息
        return this;
    }
}
