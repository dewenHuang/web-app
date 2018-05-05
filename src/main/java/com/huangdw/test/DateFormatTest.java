package com.huangdw.test;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.lang3.time.FastDateParser;
import org.junit.Test;

import java.util.Date;

/**
 * @project: my-web-app
 * @description: 日期格式化测试类
 * @author: huangdw
 * @create: 2018-04-27 16:19
 */
public class DateFormatTest {

    @Test
    public void testDateFormat() {
//        String dateStr = FastDateFormat.getInstance("yyyy-MM-dd").format(new Date());
        String dateStr = DateFormatUtils.ISO_DATE_FORMAT.format(new Date()); // yyyy-MM-dd
        System.out.println(dateStr);

//        String timeStr = FastDateFormat.getInstance("HH:mm:ss").format(new Date());
        String timeStr = DateFormatUtils.ISO_TIME_NO_T_FORMAT.format(new Date()); // HH:mm:ss
        System.out.println(timeStr);

        String dateTimeStr = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println(dateTimeStr);
    }
}
