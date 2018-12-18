package com.huangdw.utils;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @project: my-web-app
 * @description: 日期格式化辅助类（线程安全）
 * @author: huangdw
 * @create: 2018-04-27 16:33
 */
public class DateUtil {

    /** 线程安全的日期时间格式化对象 */
    private static final ThreadLocal<DateFormat> DATE_TIME_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            // 完整日期
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    /** 线程安全的日期格式化对象 */
    private static final ThreadLocal<DateFormat> DATE_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            // 年月日
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 解析日期时间
     *
     * @param dateStr   yyyy-MM-dd HH:mm:ss格式的字符串
     * @return
     * @throws ParseException
     */
    public static Date parseDateTime(String dateStr) throws ParseException {
        Date date = null;
        if (StringUtils.isNotBlank(dateStr)) {
            date = DATE_TIME_FORMAT.get().parse(dateStr);
        }
        return date;
    }

    /**
     * 解析日期
     *
     * @param dateStr   yyyy-MM-dd格式的字符串
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateStr) throws ParseException {
        Date date = null;
        if (StringUtils.isNotBlank(dateStr)) {
            date = DATE_FORMAT.get().parse(dateStr);
        }
        return date;
    }

    /**
     * 格式化日期时间
     *
     * @param date
     * @return  yyyy-MM-dd HH:mm:ss格式的字符串
     */
    public static String formatDateTime(Date date) {
        String str = "";
        if (date != null) {
            str = DATE_TIME_FORMAT.get().format(date);
        }
        return str;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return  yyyy-MM-dd格式的字符串
     */
    public static String formatDate(Date date) {
        String str = "";
        if (date != null) {
            str = DATE_FORMAT.get().format(date);
        }
        return str;
    }

    /**
     * 获取指定日期0点的字符串
     *
     * @param date
     * @return  返回示例:2017-12-23 00:00:00
     */
    public static String getZeroPointStr(Date date) {
        String str = "";
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            str = formatDateTime(calendar.getTime());
        }

        return str;
    }

    /**
     * 获取指定日期末点的字符串
     *
     * @param date
     * @return  返回示例:2017-12-23 23:59:59
     */
    public static String getLastPointStr(Date date) {
        String str = "";
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            str = formatDateTime(calendar.getTime());
        }

        return str;
    }

    /**
     * 获取指定日期的0点的毫秒数
     *
     * @param date
     * @return
     */
    public static long getZeroPointMillisecond(Date date) {
        long l = 0L;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            l = calendar.getTimeInMillis();
        }

        return l;
    }

    /**
     * 获取指定日期的末点的毫秒数
     *
     * @param date
     * @return
     */
    public static long getLastPointMillisecond(Date date) {
        long l = 0L;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            l = calendar.getTimeInMillis();
        }

        return l;
    }
}
