package com.huangdw.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @project: web-app
 * @description: 通用测试类 2
 * @author: huangdw
 * @create: 2018-08-06 18:03
 */
public class CommonTest2 {
    public static final Map<String, String> map = new HashMap<>();
    static {
        map.put("ab", "hello");
        map.put("BA", "world");
    }

    /**
     * 把Map中所有元素按照"key=value"的模式用"&"字符拼接成字符串
     *
     * @param params 参数Map
     * @param sort 是否按首字母排序
     * @param encode 如果value包含中文, 则需要URLEncode
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params, boolean sort, boolean encode) {
        List<String> keys = new ArrayList<>(params.keySet());
        // 排序
        if (sort) {
//            Collections.sort(keys); // 默认大小写敏感
            Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            // URL 编码
            if (encode) {
                try {
                    value = URLEncoder.encode(value, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (i == keys.size() - 1) {
                sb.append(key).append("=").append(value);
            } else {
                sb.append(key).append("=").append(value).append("&");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(createLinkString(map, true, true));
    }
}
