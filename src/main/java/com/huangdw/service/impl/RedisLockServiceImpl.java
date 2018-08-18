package com.huangdw.service.impl;

import com.huangdw.service.LockService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

/**
 * @project: web-app
 * @description: Redis 锁服务实现
 * @author: huangdw
 * @create: 2018-08-18 16:22
 */
public class RedisLockServiceImpl implements LockService {
    /**
     * 默认锁前缀, xxx为项目名称
     */
    private static final String DEFAULT_LOCK_PREFIX = "xxx:lock_";

    /**
     * 默认锁过期时间, 1000毫秒
     */
    private static final long DEFAULT_LOCK_EXPIRE_TIME = 1000L;

    /**
     * 默认睡眠时间, 100毫秒
     */
    private static final long DEFAULT_SLEEP_TIME = 100L;

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
     * 指定锁过期时间单位为毫秒
     */
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private static final JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);

    @Override
    public boolean tryLock(String lockKey, String requestId) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.set(DEFAULT_LOCK_PREFIX + lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, DEFAULT_LOCK_EXPIRE_TIME);
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
    public boolean tryLock(String lockKey, String requestId, long expireTime) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.set(DEFAULT_LOCK_PREFIX + lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
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
    public void lock(String lockKey, String requestId) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            for (;;) {
                String result = jedis.set(DEFAULT_LOCK_PREFIX + lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, DEFAULT_LOCK_EXPIRE_TIME);
                if(LOCK_SUCCESS.equals(result)) {
                    break;
                }

                //防止一直消耗 CPU
                Thread.sleep(DEFAULT_SLEEP_TIME);
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
    public boolean lock(String lockKey, String requestId, long blockTime) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            while (blockTime >= 0) {
                String result = jedis.set(DEFAULT_LOCK_PREFIX + lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, DEFAULT_LOCK_EXPIRE_TIME);
                if(LOCK_SUCCESS.equals(result)) {
                    return true;
                }

                //防止一直消耗 CPU
                Thread.sleep(DEFAULT_SLEEP_TIME);
                blockTime -= DEFAULT_SLEEP_TIME;
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
    public boolean unlock(String lockKey, String requestId) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(DEFAULT_LOCK_PREFIX + lockKey), Collections.singletonList(requestId));
            if (UNLOCK_SUCCESS.equals(result)) { // 此处用的 Long 类型的 equals() 方法
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
}
