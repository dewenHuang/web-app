package com.huangdw.service;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

/**
 * @project: web-app
 * @description: 限流服务
 * @author: huangdw
 * @create: 2018-06-01 15:12
 */
@Service
public class AccessLimitService {

    // 每秒放入7个令牌
    RateLimiter limiter = RateLimiter.create(6.0);

    /**
     * 尝试立即获取令牌，获取不到返回false，获取到返回true
     *
     * @return
     */
    public boolean tryAcquire() {
        return limiter.tryAcquire();
    }

    /**
     * 从RateLimiter获取一个令牌，该方法会被阻塞直到获取到令牌
     */
    public void acquire() {
        limiter.acquire();
    }
}
