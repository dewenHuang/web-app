package com.huangdw.vo;

/**
 * @author huangdw
 * @create 2018-11-07 22:21
 */
public class FieldBindErrorVo {
    /**
     * 绑定错误的字段
     */
    private String field;
    /**
     * 绑定错误的原因
     */
    private String reason;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "FieldBindErrorVo{" +
                "field='" + field + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
