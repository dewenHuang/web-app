package com.huangdw.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * 顾客实体类
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer implements Serializable {
    private static final long serialVersionUID = 6510348274667031159L;

    private Integer id;

    private String name;

    private String tel;

    private List<Ticket> tickets;// 一名顾客可以买多张车票

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}