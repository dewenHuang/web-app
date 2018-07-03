package com.huangdw.senior.interceptor;

import java.io.Serializable;

/**
 * @project: web-app
 * @description: 用户 DTO
 * @author: huangdw
 * @create: 2018-07-02 20:41
 */
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1367283892846110559L;

    private Long id;
    private String userNane;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNane() {
        return userNane;
    }

    public void setUserNane(String userNane) {
        this.userNane = userNane;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
