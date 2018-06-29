package com.huangdw.demo;

import java.io.Serializable;

/**
 * @project: web-app
 * @description: 书籍实体
 * @author: huangdw
 * @create: 2018-05-18 18:32
 */
public class BookEntity implements Serializable {

    private static final long serialVersionUID = -4849898207473407536L;

    private Integer id;
    private String name;

    public BookEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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
        this.name = name;
    }
}
