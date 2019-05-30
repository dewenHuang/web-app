package com.huangdw.test;

import com.huangdw.util.DecimalUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.StringTokenizer;

/**
 * @author huangdw
 * @date 2019/5/29
 */
public class DecimalFormatTest {
    public static void main(String[] args) throws IllegalAccessException {
        BigDecimal bd = new BigDecimal("39999991112310.515");// 建议使用字符串指定值
//        BigDecimal bd = new BigDecimal("0.515");
        System.out.println(DecimalUtil.format(bd, "#,###"));
        System.out.println(DecimalUtil.format(bd, "#,##0.00"));// 而【#,###.00】不适合对0.515进行格式化

        System.out.println(String.format("%.2f", bd));// 保留两位小数

        double v = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();// float有效数字8位，double有效数字16位
        System.out.println(v);

        System.out.println(bd.scale());

        NumberFormat percent = NumberFormat.getPercentInstance(); //建立百分比格式化引用
        percent.setMaximumFractionDigits(2); //百分比小数点最多2位
        BigDecimal interestRate = new BigDecimal("0.08515"); //利率
        System.out.println("利率:\t" + percent.format(interestRate)); //利率: 8.52%

        StringTokenizer st = new StringTokenizer( "123,456,789.125", ",");
        StringBuffer sb = new StringBuffer();
        while(st.hasMoreTokens())   {
            sb.append(st.nextToken());
        }
        System.out.println(sb); //123456789.125

        String str = "123,456,789.125";
        str = str.replace(",", "");
//        str = str.replaceAll("\\d", "*");
        System.out.println(str);// 123456789.125

        System.out.println(DecimalUtil.div(6.2, 3.2, 2, RoundingMode.HALF_UP));// 1.9375->1.94
    }
}
