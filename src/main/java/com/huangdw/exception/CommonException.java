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

    /**
     * 错误文案
     */
    private String errorMsg;

    public CommonException(ErrorEnum error) { // 使用默认文案
        super(error.getMsg());
        this.errorMsg = error.getMsg();
        this.error = error;
    }

    public CommonException(String errorMsg, ErrorEnum error) { // 重写文案
        super("[" + error.getMsg() + "]-" + errorMsg);
        this.errorMsg = errorMsg;
        this.error = error;
    }

    public CommonException(String message, String errorMsg, ErrorEnum error) {
        super(message);
        this.errorMsg = errorMsg;
        this.error = error;
    }

    public CommonException(String message, Throwable cause, String errorMsg, ErrorEnum error) {
        super(message, cause);
        this.errorMsg = errorMsg;
        this.error = error;
    }

    public ErrorEnum getError() {
        return error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
