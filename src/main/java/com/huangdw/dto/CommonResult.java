package com.huangdw.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huangdw.enums.RespEnum;

import java.io.Serializable;

/**
 * @program: my-controller-app
 * @description: 通用结果类
 * @author: huangdw
 * @create: 2018-04-13
 */
public class CommonResult implements Serializable {
    private static final long serialVersionUID = 7784661672912742703L;

    /** 返回码 */
    private Integer code;
    /** 返回消息 */
    private String msg;
    /** 业务数据 */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public CommonResult() {
    }

    public CommonResult(RespEnum respEnum, Object data) {
        this.code = respEnum.getCode();
        this.msg = respEnum.getMsg();
        this.data = data;
    }

    /**
     * 成功
     *
     * @return
     */
    public static CommonResult success() {
        return new CommonResult(RespEnum.OK, null);
    }

    /**
     * 成功（带业务数据）
     *
     * @param data
     * @return
     */
    public static CommonResult success(Object data) {
        return new CommonResult(RespEnum.OK, data);
    }

    /**
     * 失败
     *
     * @param respEnum
     * @return
     */
    public static CommonResult fail(RespEnum respEnum) {
        return new CommonResult(respEnum, null);
    }

    /**
     * 失败（带业务数据）
     *
     * @param respEnum
     * @param data
     * @return
     */
    public static CommonResult fail(RespEnum respEnum, Object data) {
        return new CommonResult(respEnum, data);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
