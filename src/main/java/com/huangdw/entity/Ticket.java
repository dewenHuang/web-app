package com.huangdw.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 车票实体类
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ticket implements Serializable {
    private static final long serialVersionUID = 8574696411390214055L;

    private Integer id;

    private String address;

    private Integer price;

    private Integer custId;

    private Customer customer;// 一张车票只属于一名顾客

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", custId=" + custId +
                ", customer=" + customer +
                '}';
    }
}