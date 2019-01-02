package com.huangdw.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

/**
 * JSON 工具类
 *
 * @author huangdw
 * @date 2019/1/2
 */
public class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /** JSON 格式化输出 */
    private static final ObjectWriter OBJECT_WRITER = OBJECT_MAPPER.writer().withDefaultPrettyPrinter();

    private JsonUtil() {
    }

    /**
     * 对象转换 JSON
     *
     * @param obj
     * @return
     */
    public static String obj2Json(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            LOGGER.warn("Convert to json error", e);
        }

        return null;
    }

    /**
     * 对象转换 JSON（支持格式化输出）
     *
     * @param obj
     * @param format
     * @return
     */
    public static String obj2Json(Object obj, boolean format) {
        try {
            if (!format) {
                return OBJECT_MAPPER.writeValueAsString(obj);
            } else {
                return OBJECT_WRITER.writeValueAsString(obj);
            }
        } catch (Exception e) {
            LOGGER.warn("Convert to json error", e);
        }

        return null;
    }

    /**
     * JSON 字符串转换对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T json2Obj(String json, Class<T> clazz) {
        if (StringUtils.isNotBlank(json)) {
            try {
                return OBJECT_MAPPER.readValue(json, clazz);
            } catch (Exception e) {
                LOGGER.warn("Json parse error", e);
            }
        }

        return null;
    }

    /**
     * JSON 字符串转换泛型对象
     *
     * @param json
     * @param typeReference
     * @param <T>
     * @return
     */
    public <T> T json2GGenericObj(String json, TypeReference<T> typeReference) {
        if (StringUtils.isNotBlank(json)) {
           try {
                return OBJECT_MAPPER.readValue(json, typeReference);
            } catch (Exception e) {
                LOGGER.warn("Json parse error", e);
            }
        }

        return null;
    }
}
