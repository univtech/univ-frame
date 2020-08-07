/**
 * 
 */
package com.sunsharing.xshare.framework.core.lang;

/**
 * 数字工具类，用于处理：byte/Byte、short/Short、int/Integer、long/Long、float/Float、double/Double。<br>
 * <p>
 *
 * 参考：<br>
 * org.apache.commons.lang3.math.NumberUtils<br>
 *
 * @author Kison 2017年3月31日
 */
public class NumberUtils {

    /**
     * 把String转化为int。<br>
     * 
     * @param stringValue 字符串
     * @param defaultValue 默认值
     * @return int
     */
    public static int toInt(String stringValue, int defaultValue) {
        return org.apache.commons.lang3.math.NumberUtils.toInt(stringValue, defaultValue);
    }

    /**
     * 把String转化为long。<br>
     * 
     * @param stringValue 字符串
     * @param defaultValue 默认值
     * @return long
     */
    public static long toLong(String stringValue, long defaultValue) {
        return org.apache.commons.lang3.math.NumberUtils.toLong(stringValue, defaultValue);
    }

    /**
     * 把String转化为double。<br>
     * 
     * @param stringValue 字符串
     * @param defaultValue 默认值
     * @return double
     */
    public static double toDouble(String stringValue, double defaultValue) {
        return org.apache.commons.lang3.math.NumberUtils.toDouble(stringValue, defaultValue);
    }

}
