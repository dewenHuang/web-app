package com.huangdw.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单实体
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order implements Serializable {
    private static final long serialVersionUID = -6942179698713525509L;

    private Integer id;

    private Integer userId;

    private String number;

    private String note;

    private Date createTime;

    /** 订单的明细 */
    private List<OrderDetail> orderDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", number='" + number + '\'' +
                ", note='" + note + '\'' +
                ", createTime=" + createTime +
                ", orderDetails=" + orderDetails +
                '}';
    }
}