package com.huangdw.senior.interceptor;

import org.springframework.stereotype.Service;

/**
 * @project: web-app
 * @description: 用户服务接口实现
 * @author: huangdw
 * @create: 2018-07-02 20:35
 */
@Service
public class UserServiceImpl implements UserService {

    // 注入 UserMapper

    @Override
    public UserDTO getCurrUser(long id) {
        // 调用 UserMapper 的 selectByPrimaryKey 方法得到 UserEntity 转化 UserDTO 并返回
        return null;
    }
}
