package com.huangdw.util;

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
     * 解析完整日期
     *
     * @param dateStr   yyyy-MM-dd HH:mm:ss格式的字符串
     * @return
     * @throws ParseException
     */
    public static Date parseDateTime(String dateStr) throws ParseException {
        Date date = DATE_TIME_FORMAT.get().parse(dateStr);
        return date;
    }

    /**
     * 解析年月日
     *
     * @param dateStr   yyyy-MM-dd格式的字符串
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateStr) throws ParseException {
        Date date = DATE_FORMAT.get().parse(dateStr);
        return date;
    }

    /**
     * 格式化完整日期
     *
     * @param date
     * @return  yyyy-MM-dd HH:mm:ss格式的字符串
     */
    public static String formatDateTime(Date date) {
        String s = DATE_TIME_FORMAT.get().format(date);
        return s;
    }

    /**
     * 格式化年月日
     *
     * @param date
     * @return  yyyy-MM-dd格式的字符串
     */
    public static String formatDate(Date date) {
        String s = DATE_FORMAT.get().format(date);
        return s;
    }

    /**
     * 获取指定日期0点的字符串
     *
     * @param date
     * @return  返回示例:2017-12-23 00:00:00
     */
    public static String getZeroPointStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return formatDateTime(calendar.getTime());
    }

    /**
     * 获取指定日期末点的字符串
     *
     * @param date
     * @return  返回示例:2017-12-23 23:59:59
     */
    public static String getLastPointStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return formatDateTime(calendar.getTime());
    }

    /**
     * 获取指定日期的0点的毫秒数
     *
     * @param date
     * @return
     */
    public static long getZeroPointMillisecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取指定日期的末点的毫秒数
     *
     * @param date
     * @return
     */
    public static long getLastPointMillisecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTimeInMillis();
    }
}
