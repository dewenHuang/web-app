package com.huangdw.senior.interceptor;

/**
 * @project: web-app
 * @description: 用户服务接口
 * @author: huangdw
 * @create: 2018-07-02 20:36
 */
public interface UserService {

    /**
     * 查询当前登录用户的信息
     *
     * @param id
     * @return
     */
    UserDTO getCurrUser(long id);
}
