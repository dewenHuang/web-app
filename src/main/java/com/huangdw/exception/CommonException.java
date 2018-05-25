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

    /**
     * 参数数组
     */
    private Object[] paramArr;

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

    public CommonException(ErrorEnum error, Object[] paramArr) { // 使用默认文案，并打印业务参数
        super(error.getMsg());
        this.error = error;
        this.errorMsg = error.getMsg();
        this.paramArr = paramArr;
    }

    public CommonException(ErrorEnum error, String errorMsg, Object[] paramArr) { // 重写文案，并打印业务参数
        super("[" + error.getMsg() + "]->" + errorMsg);
        this.error = error;
        this.errorMsg = errorMsg;
        this.paramArr = paramArr;
    }

    public ErrorEnum getError() {
        return error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 获取参数描述
     *
     * @return
     */
    public String getParamDesc() {
        String paramDesc = "";
        if (paramArr != null && paramArr.length > 0) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("{");
            Object param;
            for (int i = 0; i < paramArr.length; i++) {
                param = paramArr[i];
                if (param != null) {
                    buffer.append("Param").append(i + 1).append("=");
                    buffer.append(JSON.toJSONString(param));
                    if (i != paramArr.length - 1) {
                        buffer.append(";");
                    }
                }
            }
            buffer.append("}.");
            paramDesc = buffer.toString();
        }

        return paramDesc;
    }
}
