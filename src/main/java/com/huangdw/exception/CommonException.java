package com.huangdw.exception;

import com.huangdw.enums.XxxErrorEnum;

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
     * Xxx错误
     */
    private XxxErrorEnum xxxError; // 如果XxxErrorEnum按业务进行划分，则此处继续添加YyyErrorEnum域属性等；当然还可以根据业务场景自定义多个异常类

    /**
     * 错误消息
     */
    private String errMsg;

    public CommonException(Throwable cause, XxxErrorEnum xxxError, String errMsg) {
        super(cause);
        this.xxxError = xxxError;
        this.errMsg = errMsg;
    }

    public CommonException(String message, XxxErrorEnum xxxError, String errMsg) {
        super(message);
        this.xxxError = xxxError;
        this.errMsg = errMsg;
    }

    public CommonException(String message, Throwable cause, XxxErrorEnum xxxError, String errMsg) {
        super(message, cause);
        this.xxxError = xxxError;
        this.errMsg = errMsg;
    }

    public CommonException(XxxErrorEnum xxxError, String errMsg) {
        super("[" + xxxError.getMsg() + "]-" + errMsg);
        this.xxxError = xxxError;
        this.errMsg = errMsg;
    }

    public XxxErrorEnum getXxxError() {
        return xxxError;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
