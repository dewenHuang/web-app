package com.huangdw.entity;

import java.io.Serializable;

/**
 * 雇员实体
 *
 * @author huangdw
 * @create 2018-11-07 20:27
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = -2598805072031127689L;

    private Integer id;
    private String name;
    private Integer age;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
