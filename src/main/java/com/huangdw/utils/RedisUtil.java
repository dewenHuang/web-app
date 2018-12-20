package com.huangdw.utils;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类
 *
 * @author huangdw
 * @date 2018/12/18
 */
public class RedisUtil {
    /**
     * 设置给定 key 的值
     *
     * @param redisTemplate
     * @param key
     * @param value
     * @param ttl
     */
    public static void set(RedisTemplate redisTemplate, Object key, Object value, long ttl) {
        if (ttl > -1) {
            redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
        } else {// 永不过期
            redisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * 为哈希表中的字段赋值
     *
     * @param redisTemplate
     * @param key
     * @param field
     * @param value
     * @param ttl
     */
    public static Boolean hSet(RedisTemplate redisTemplate, final Object key, final Object field, final Object value, final long ttl) {
        Object result = redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                Boolean success = Boolean.TRUE;

                if (ttl > -1) {
                    try {
                        operations.multi();
                        operations.opsForHash().put(key, field, value);
                        operations.expire(key, ttl, TimeUnit.SECONDS);
                        List execResults = operations.exec();

                        // 判断事务块内的所有命令是否都执行成功
                        if (CollectionUtils.isEmpty(execResults)) {
                            success = Boolean.FALSE;
                        }
                        for (Object execResult : execResults) {
                            if (!(Boolean) execResult) {
                                success = Boolean.FALSE;
                                break;
                            }
                        }
                    } catch (Exception e) {
                        operations.discard();
                        success = Boolean.FALSE;
                    }
                } else {
                    operations.opsForHash().put(key, field, value);
                }

                return success;
            }
        });

        return (Boolean) result;
    }

    /**
     * 在指定的 key 不存在时，为 key 设置指定的值
     *
     * @param redisTemplate
     * @param key
     * @param value
     * @param ttl
     * @return
     */
    public static Boolean setNx(final RedisTemplate redisTemplate, final Object key, final Object value, final long ttl) {
        Object result = redisTemplate.execute(new SessionCallback<Boolean>() {
            @Override
            public Boolean execute(RedisOperations operations) throws DataAccessException {
                Boolean success = Boolean.TRUE;

                if (ttl > -1) {
                    try {
                        operations.multi();// 标记一个事务块的开始
                        operations.opsForValue().setIfAbsent(key, value);// setNx
                        operations.expire(key, ttl, TimeUnit.SECONDS);// expire
                        List execResults = operations.exec();// 执行所有事务块内的命令

                        // 判断事务块内的所有命令是否都执行成功
                        if (CollectionUtils.isEmpty(execResults)) {
                            success = Boolean.FALSE;
                        }
                        for (Object execResult : execResults) {
                            if (!((Boolean) execResult)) {
                                success = Boolean.FALSE;
                                break;
                            }
                        }
                    } catch (Exception e) {
                        operations.discard();// 取消事务，放弃执行事务块内的所有命令（回滚）
                        success = Boolean.FALSE;
                    }
                } else {
                    operations.opsForValue().setIfAbsent(key, value);// setNx
                }

                return success;
            }
        });

        return (Boolean) result;
    }

    /**
     * 为哈希表中不存在的的字段赋值
     *
     * @param redisTemplate
     * @param key
     * @param field
     * @param value
     * @param ttl
     * @return
     */
    public static Boolean hSetNx(final RedisTemplate redisTemplate, final Object key, final Object field, final Object value, final long ttl) {
        Object result = redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                Boolean success = Boolean.TRUE;

                if (ttl > -1) {
                    try {
                        operations.multi();
                        operations.opsForHash().putIfAbsent(key, field, value);
                        operations.expire(key, ttl, TimeUnit.SECONDS);
                        List<Object> execResults = operations.exec();

                        // 判断事务块内的所有命令是否都执行成功
                        if (CollectionUtils.isEmpty(execResults)) {
                            success = Boolean.FALSE;
                        }
                        for (Object execResult : execResults) {
                            if (!((Boolean) execResult)) {
                                success = Boolean.FALSE;
                                break;
                            }
                        }
                    } catch (Exception e) {
                        operations.discard();
                        success = Boolean.FALSE;
                    }
                } else {
                    operations.opsForHash().putIfAbsent(key, field, value);
                }

                return success;
            }
        });

        return (Boolean) result;
    }

    /**
     * 删除已存在的键
     *
     * @param redisTemplate
     * @param key
     * @return
     */
    public static Boolean del(RedisTemplate redisTemplate, Object key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除哈希表 key 中的一个或多个指定字段，不存在的字段将被忽略
     *
     * @param redisTemplate
     * @param key
     * @param fields
     * @return  被成功删除字段的数量，不包括被忽略的字段
     */
    public static Long hDel(RedisTemplate redisTemplate, final Object key, final Object... fields) {
        return redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 删除当前库的所有 key
     *
     * @param redisTemplate
     */
    public static void flushDb(RedisTemplate redisTemplate) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return null;
            }
        });
    }

    /**
     * 将 key 中储存的数字加上指定的增量值
     *
     * @param redisTemplate
     * @param key
     * @param increment
     * @return
     */
    public static Long incrBy(RedisTemplate redisTemplate, Object key, long increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * 为哈希表中的字段值加上指定增量值
     *
     * @param redisTemplate
     * @param key
     * @param field
     * @param increment
     * @return  执行 HINCRBY 命令之后，哈希表中字段的值
     */
    public static Long hIncrBy(RedisTemplate redisTemplate, Object key, Object field, long increment) {
        return redisTemplate.opsForHash().increment(key, field, increment);
    }

    /**
     * 设置 key 的过期时间
     *
     * @param redisTemplate
     * @param key
     * @param ttl
     * @return
     */
    public static Boolean expire(RedisTemplate redisTemplate, Object key, long ttl) {
        if (ttl > -1) {
            return redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
        } else {// 永不过期
            return redisTemplate.persist(key);
        }
    }

    /**
     * 以秒为单位返回 key 的剩余过期时间
     *
     * @param redisTemplate
     * @param key
     * @return
     */
    public static Long ttl(RedisTemplate redisTemplate, Object key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 获取指定 key 的值
     *
     * @param redisTemplate
     * @param key
     */
    public static Object get(RedisTemplate redisTemplate, Object key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 返回哈希表中指定字段的值
     *
     * @param redisTemplate
     * @param key
     * @param field
     * @return
     */
    public static Object hGet(RedisTemplate redisTemplate, Object key, Object field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 检查给定的 key 是否存在
     *
     * @param redisTemplate
     * @param key
     * @return
     */
    public static Boolean exists(RedisTemplate redisTemplate, Object key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 查看哈希表的指定字段是否存在
     *
     * @param redisTemplate
     * @param key
     * @param field
     * @return
     */
    public static Boolean hExists(RedisTemplate redisTemplate, Object key, Object field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }
}
