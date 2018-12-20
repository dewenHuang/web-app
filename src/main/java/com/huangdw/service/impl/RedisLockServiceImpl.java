package com.huangdw.service.impl;

import com.huangdw.service.LockService;
import org.apache.commons.collections.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.Collections;
import java.util.List;

/**
 * @project: web-app
 * @description: Redis 锁服务实现
 * @author: huangdw
 * @create: 2018-08-18 16:22
 */
public class RedisLockServiceImpl implements LockService {
    /**
     * 锁前缀, xxx为项目名称
     */
    private static final String LOCK_PREFIX = "xxx:lock_";

    /**
     * 加锁成功
     */
    private static final String LOCK_SUCCESS = "OK";

    /**
     * 解锁成功
     */
    private static final Long UNLOCK_SUCCESS = 1L;

    /**
     * key 不存在时, 设值
     */
    private static final String SET_IF_NOT_EXIST = "NX";

    /**
     * 指定锁的有效时间单位为秒
     */
    private static final String EXPIRE_TIME_UNIT_SEC = "EX";

    /**
     * Redis 客户端连接池
     */
    private static final JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);

    @Override
    public boolean tryLock(String lockKey, String identifier, long expireTime) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();// 客户端应该通过注入或者参数传进来
            String result = jedis.set(LOCK_PREFIX + lockKey, identifier, SET_IF_NOT_EXIST, EXPIRE_TIME_UNIT_SEC, expireTime);
            if(LOCK_SUCCESS.equals(result)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    @Override
    public boolean tryLock(String lockKey, String identifier, long expireTime, long timeout) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            long end = System.currentTimeMillis() + timeout;
            while (System.currentTimeMillis() < end) {
                String result = jedis.set(LOCK_PREFIX + lockKey, identifier, SET_IF_NOT_EXIST, EXPIRE_TIME_UNIT_SEC, expireTime);
                if(LOCK_SUCCESS.equals(result)) {
                    return true;
                }

                // 防止一直消耗 CPU
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    @Override
    public void lock(String lockKey, String identifier, long expireTime) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            for (;;) {// while (true)
                String result = jedis.set(LOCK_PREFIX + lockKey, identifier, SET_IF_NOT_EXIST, EXPIRE_TIME_UNIT_SEC, expireTime);
                if(LOCK_SUCCESS.equals(result)) {
                    break;
                }

                // 防止一直消耗 CPU
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public boolean unlock(String lockKey, String identifier) {
        Jedis jedis = jedisPool.getResource();
        try {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(LOCK_PREFIX + lockKey), Collections.singletonList(identifier));
            if (UNLOCK_SUCCESS.equals(result)) {// 此处用的 Long 类型的 equals 方法
                return true;
            }
        } catch (Exception e) {
            // Redis不支持EVAL命令，使用降级方式解锁
            while (true) {
                // 监视锁键
                jedis.watch(LOCK_PREFIX + lockKey);
                // 通过加锁方法返回的锁标识identifier判断是不是该锁，若是该锁，则释放锁
                if (identifier.equals(jedis.get(LOCK_PREFIX + lockKey))) {
                    Transaction transaction = jedis.multi();
                    try {
                        transaction.del(LOCK_PREFIX + lockKey);
                        List<Object> execResults = transaction.exec();// 不需要再执行 UNWATCH 了
                        if (CollectionUtils.isEmpty(execResults)) {
                            continue;
                        }
                        return true;
                    } catch (Exception ex) {
                        transaction.discard();// 不需要再执行 UNWATCH 了
                        continue;
                    }
                }
                jedis.unwatch();// 取消监视锁键
                break;
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }
}
