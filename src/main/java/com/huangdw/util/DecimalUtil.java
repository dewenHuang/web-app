package com.huangdw.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author huangdw
 * @date 2019/5/29
 */
public class DecimalUtil {
    /**
     * 格式化金额（比如：以千位分隔、保留几位小数等）
     *
     * @param bd 39999991112310.515
     * @param pattern #,### or #,##0.00
     * @return 39,999,991,112,311 or 39,999,991,112,310.52
     */
    public static String format(BigDecimal bd, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(bd);
    }

    /**
     * 格式化比例（比如：百分比）
     *
     * @param bd 0.08515
     * @return  9%
     */
    public static String percentage(BigDecimal bd) {
        NumberFormat nf = NumberFormat.getPercentInstance();
//        nf.setMaximumFractionDigits(2);
        return nf.format(bd);
    }

    /**
     * 金额货币展示
     *
     * @param bd 150.48
     * @return ￥150.48
     */
    public static String currency(BigDecimal bd) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nf.format(bd);
    }

    /**
     * 提供精确加法计算的add方法
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static double add(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确减法运算的sub方法
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static double sub(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确乘法运算的mul方法
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double mul(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确的除法运算的div方法
     *
     * @param value1 被除数
     * @param value2 除数
     * @param scale 精确范围
     * @param roundingMode 舍入模式
     * @return 两个参数的商
     * @throws IllegalAccessException
     */
    public static double div(double value1, double value2, int scale, RoundingMode roundingMode) throws IllegalAccessException {
        //如果精确范围小于0，抛出异常信息
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.divide(b2, scale, roundingMode).doubleValue();
    }
}
