package com.huangdw.test;

import com.alibaba.fastjson.JSON;
import com.huangdw.enums.XxxErrorEnum;
import com.huangdw.exception.CommonException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: demo
 * @description: logback日志测试类
 * @author: huangdw
 * @create: 2018-03-31
 */
public class LogTest {
    /**
     * 日志记录器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void testLog() {
        LOGGER.debug("logback 打印 debug 日志");
        LOGGER.info("logback 打印 info 日志");
        LOGGER.error("logback 打印 error 日志");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", 1);
        paramMap.put("name", "黄德文");
        paramMap.put("age", 32);
        LOGGER.info("user info: {}", JSON.toJSONString(paramMap));
    }
}
