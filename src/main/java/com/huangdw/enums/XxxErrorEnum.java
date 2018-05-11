package com.huangdw.enums;

/**
 * @program: my-controller-app
 * @description: Xxx错误枚举（包含各个业务异常枚举）
 * @author: huangdw
 * @create: 2018-04-13
 */
public enum XxxErrorEnum implements ErrorEnum {

    // 用户方面错误1xxx
    USERNAME_ERROR(1000, "用户名错误"),
    PASSWORD_ERROR(1001, "用户密码错误"),
    // 资源错误2xxx
    RESOURCE_EXPIRE(2000, "资源过期"),
    RESOURCE_NOTFOUND(2001, "资源不存在"),
    // 参数错误3xxx
    PARAMETER_NULL(3000, "参数为空"),
    PARAMETER_ILLEGAL(3001, "参数非法"),
    // 系统错误
    SYSTEM_ERROR(9000, "服务器异常，请稍后重试");

    // 错误编码
    private final int code;
    // 错误消息
    private final String msg;

    XxxErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据返回码获取枚举对象
     *
     * @param code  状态码
     * @return
     */
    public static XxxErrorEnum getByCode(int code) {
        for (XxxErrorEnum errorEnum : XxxErrorEnum.values()) {
            if (errorEnum.getCode() == code) {
                return errorEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
