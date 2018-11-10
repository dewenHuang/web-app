package com.huangdw.enums;

/**
 * 返回状态枚举
 *
 * @author Devin
 * @date 2018/11/10
 */
public enum ReturnStatusEnum {
    SUCCESS(0, "成功"),

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
    private final int code;
    // 状态消息
    private final String msg;

    ReturnStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 根据状态码获取状态枚举
     *
     * @param code
     * @return
     */
    public static ReturnStatusEnum getEnumByCode(int code) {
        for (ReturnStatusEnum status : ReturnStatusEnum.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }

        return null;
    }
}
