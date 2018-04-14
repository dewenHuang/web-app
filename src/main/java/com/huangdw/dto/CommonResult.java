package com.huangdw.dto;

import com.huangdw.enums.XxxErrorEnum;

import java.io.Serializable;

/**
 * @program: my-controller-app
 * @description: 通用结果类
 * @author: huangdw
 * @create: 2018-04-13
 */
public class CommonResult<T> implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7784661672912742703L;

    /**
     * 返回码
     */
    private int code = 0;

    /**
     * 错误消息
     */
    private String msg;

    /**
     * 业务数据
     */
    private T data;

    /**
     * 请求成功
     */
    public CommonResult() {
    }

    /**
     * 请求成功（带数据）
     *
     * @param data
     */
    public CommonResult(T data) {
        this.data = data;
    }

    /**
     * 请求失败
     *
     * @param errorEnum
     */
    public CommonResult(XxxErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.msg = errorEnum.getMsg();
    }

    /**
     * 请求失败（重写错误消息）
     *
     * @param errorEnum
     * @param errMsg
     */
    public CommonResult(XxxErrorEnum errorEnum, String errMsg) {
        this.code = errorEnum.getCode();
        this.msg = errMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
