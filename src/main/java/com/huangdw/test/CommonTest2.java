package com.huangdw.test;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static boolean isMsInTimeRange(long ms, String beginTime, String endTime) throws ParseException {
        Date d = new Date(ms);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin = df.parse(beginTime);
        Date end = df.parse(endTime);
        return d.after(begin) && d.before(end);
    }

    public static String convertMsToTime(long ms) throws ParseException {
        Date d = new Date(ms);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(d);
    }

    public static void main(String[] args) throws ParseException {
//        System.out.println(createLinkString(map, true, true));

//        System.out.println(isMsInTimeRange(10832080, "2016-07-07 14:09:49", "2016-07-07 22:58:51"));
//        System.out.println(convertMsToTime(10832080));

//        int i = 1;
//        Integer i2 = 1;
//        Integer j = 127;
//        Integer j2 = 127;
//        System.out.println(i == i2);
//        System.out.println(j == j2);// true，128结果就是false

        Object obj = null;
        Integer i3 = (Integer) obj;
        System.out.println(null == i3);

        String s1 = null;
        String s2 = "";
        String s3 = " ";
        String s4 = "  ";
        System.out.println(StringUtils.isBlank(s1));
        System.out.println(StringUtils.isBlank(s2));
        System.out.println(StringUtils.isBlank(s3));
        System.out.println(StringUtils.isBlank(s4));
    }
}
