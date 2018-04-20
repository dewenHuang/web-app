package com.huangdw.test;

import com.alibaba.fastjson.JSON;
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

    public static void main(String[] args) {
        LOGGER.debug("logback 成功了");
        LOGGER.info("logback 成功了");
        LOGGER.error("logback 成功了");

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("id", "1");
        paramMap.put("name", "黄德文");
        paramMap.put("age", "32");
        LOGGER.info("user info: {}", JSON.toJSONString(paramMap));
    }
}
