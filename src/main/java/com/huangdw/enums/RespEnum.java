package com.huangdw.enums;

/**
 * 响应状态枚举类
 *
 * @author Devin
 * @date 2018/11/10
 */
public enum RespEnum {
    OK(200, "正常"),
    CLIENT_ERROR(400, "客户端错误"),
    SERVER_FAIL(500, "服务器端异常"),

    // 客户端详细错误
    REQUEST_NOT_FOUND(401, "请求方法找不到"),
    REQUEST_METHOD_NOT_ALLOWED(402, "请求方式不允许"),
    REQUEST_BAD(403, "请求语法不正确"),

    // 1xxxx: 服务器相关错误
    INTERNAL_SERVER_ERROR(10000, "服务器内部异常"),
    INTERFACE_UNAVAILABLE(10001, "接口不可用"),
    BUSINESS_ERROR(10002, "业务异常"),

    // 2xxxx: 客户端相关错误，含参数校验不通过等
    USER_NOT_LOGIN(20000, "用户未登录"),
    USER_LOGIN_EXPIRE(20001, "用户登录态过期"),
    PARAMETER_ERROR(20002, "参数错误"),
    CONTAIN_SENSITIVE_WORD(20003, "包含敏感字"),
    LACK_REQUIRE_PARAMETER(20004, "缺少必传参数"),

    // 3xxxx: 其他特殊错误
    ;

    // 状态码
    private final Integer code;
    // 状态消息
    private final String msg;

    RespEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {// 重写 toString 方法，默认返回 name（比如 OK）
        return msg;
    }

    /**
     * 根据状态码获取状态枚举
     *
     * @param code
     * @return
     */
    public static RespEnum getEnumByCode(Integer code) {
        for (RespEnum respEnum : RespEnum.values()) {
            if (respEnum.getCode().equals(code)) {
                return respEnum;
            }
        }

        return null;
    }
}
