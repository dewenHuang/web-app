package com.huangdw.exception;

import com.huangdw.enums.ErrorEnum;

/**
 * @program: my-controller-app
 * @description: 公共异常类
 * @author: huangdw
 * @create: 2018-04-13
 */
public class CommonException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5816725436661829830L;

    /**
     * 错误枚举接口
     */
    private ErrorEnum error;

    public CommonException(ErrorEnum error) {
        super(error.getMsg()); // 如果抛出自定义异常时没有指定异常消息，则使用错误枚举的错误消息
        this.error = error;
    }

    public CommonException(String message, ErrorEnum error) { // 常用，message（可变详细）->开发人员，错误枚举里面的msg（固定通用）->前端用户
        super(message);
        this.error = error;
    }

    public CommonException(String message, Throwable cause, ErrorEnum error) { // 异常堆栈信息跟踪
        super(message, cause);
        this.error = error;
    }

    public ErrorEnum getError() {
        return error;
    }
}
