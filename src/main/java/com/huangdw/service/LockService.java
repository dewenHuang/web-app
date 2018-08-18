package com.huangdw.service;

/**
 * @project: web-app
 * @description: 锁服务接口
 * @author: huangdw
 * @create: 2018-08-18 16:21
 */
public interface LockService {
    /**
     * 非阻塞锁
     *
     * @param lockKey
     * @param requestId
     * @return
     */
    boolean tryLock(String lockKey, String requestId);

    /**
     * 非阻塞锁 + 锁过期时间
     *
     * @param lockKey
     * @param requestId
     * @param expireTime
     * @return
     */
    boolean tryLock(String lockKey, String requestId, long expireTime);

    /**
     * 阻塞锁
     *
     * @param lockKey
     * @param requestId
     */
    void lock(String lockKey, String requestId);

    /**
     * 阻塞锁 + 获取锁阻塞时间
     *
     * @param lockKey
     * @param requestId
     * @param blockTime
     * @return
     */
    boolean lock(String lockKey, String requestId, long blockTime);

    /**
     * 解锁
     *
     * @param lockKey
     * @param requestId
     * @return
     */
    boolean unlock(String lockKey, String requestId);
}
