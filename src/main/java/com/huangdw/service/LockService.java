package com.huangdw.service;

/**
 * @project: web-app
 * @description: 锁服务接口
 * @author: huangdw
 * @create: 2018-08-18 16:21
 */
public interface LockService {
    /**
     * 尝试获取锁，立即返回
     *
     * @param lockKey 锁键
     * @param identifier 锁标识，应该是一个随机字符串，比如用 UUID 生成
     * @param expireTime 锁过期时间
     * @return 是否成功获得锁
     */
    boolean tryLock(String lockKey, String identifier, long expireTime);

    /**
     * 尝试获取锁，超时返回，常用
     *
     * @param lockKey 锁键
     * @param identifier 锁标识
     * @param expireTime 锁过期时间
     * @param timeout 获取锁超时时间，单位为毫秒
     * @return 是否成功获得锁
     */
    boolean tryLock(String lockKey, String identifier, long expireTime, long timeout);

    /**
     * 以阻塞方式的获取锁，直到成功获得锁为止
     *
     * @param lockKey 锁键
     * @param identifier 锁标识
     * @param expireTime 锁过期时间
     */
    void lock(String lockKey, String identifier, long expireTime);

    /**
     * 解锁
     *
     * @param lockKey 锁键
     * @param identifier 锁标识
     * @return 是否成功释放锁
     */
    boolean unlock(String lockKey, String identifier);
}
