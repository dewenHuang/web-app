package com.huangdw.exception;

import com.alibaba.fastjson.JSON;
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
        this.error = error;
        this.errorMsg = error.getMsg();
    }

    public CommonException(ErrorEnum error, String errorMsg) { // 重写文案
        super("[" + error.getMsg() + "]->" + errorMsg);
        this.error = error;
        this.errorMsg = errorMsg;
    }

    public ErrorEnum getError() {
        return error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
