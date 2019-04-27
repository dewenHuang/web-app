package com.huangdw.test;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @project: my-web-app
 * @description: 日期格式化测试类
 * @author: huangdw
 * @create: 2018-04-27 16:19
 */
public class DateFormatTest {

    @Test
    public void testDateFormat() throws ParseException {
//        String dateStr = FastDateFormat.getInstance("yyyy-MM-dd").format(new Date());
        String dateStr = DateFormatUtils.ISO_DATE_FORMAT.format(new Date());// yyyy-MM-dd
        System.out.println(dateStr);

//        String timeStr = FastDateFormat.getInstance("HH:mm:ss").format(new Date());
        String timeStr = DateFormatUtils.ISO_TIME_NO_T_FORMAT.format(new Date());// HH:mm:ss
        System.out.println(timeStr);

//        String dateTimeStr = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date());
        String dateTimeStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");// yyyy-MM-dd HH:mm:ss
        System.out.println(dateTimeStr);

        Date date = DateUtils.parseDate("2019-3-1 15:56:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println(date);

        // =====================对于可能出现的运行时异常要提前判断，比如在解析日期字符串前先判空=====================
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.parse(null);// NullPointerException

        DateUtils.parseDate(null, "yyyy-MM-dd HH:mm:ss");// IllegalArgumentException
    }
}
