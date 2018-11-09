package com.huangdw.exception;

import com.huangdw.enums.ErrorEnum;
import org.apache.commons.lang.ArrayUtils;

/**
 * @program: my-controller-app
 * @description: 自定义异常
 * @author: huangdw
 * @create: 2018-04-13
 */
public class CustomException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5816725436661829830L;

    /**
     * 错误枚举接口
     */
    private ErrorEnum error;

    /**
     * 错误文案, 用于打印错误日志
     */
    private String errorMsg;

    /**
     * 参数数组, 用于打印错误日志时显示入参
     */
    private Object[] paramArr;

    public CustomException(ErrorEnum error) { // 使用默认文案
        super(error.getMsg());
        this.error = error;
        this.errorMsg = error.getMsg();
    }

    public CustomException(ErrorEnum error, String errorMsg) { // 重写文案
        super(errorMsg);
        this.error = error;
        this.errorMsg = errorMsg;
    }

    public CustomException(ErrorEnum error, Object[] paramArr) {
        super(error.getMsg());
        this.error = error;
        this.errorMsg = error.getMsg();
        this.paramArr = paramArr;
    }

    public String getParamDesc() {
        StringBuilder sb = new StringBuilder();
        if (ArrayUtils.isNotEmpty(paramArr)) {
            sb.append("{");
            for (int i = 0; i < paramArr.length; i++) {
                if (i != 0) {
                    sb.append("|");
                }
                sb.append(paramArr[i]);
            }
            sb.append("}");
        }

        return sb.toString();
    }

    public ErrorEnum getError() {
        return error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
