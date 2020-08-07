/**
 * 
 */
package com.sunsharing.xshare.framework.core.lang;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类，用于处理Date和Time。<br>
 * <p>
 *
 * 参考：<br>
 * org.apache.commons.lang3.time.DateUtils<br>
 * org.apache.commons.lang3.time.DateFormatUtils<br>
 *
 * @author Kison 2017年3月31日
 */
public class DateUtils {

    /**
     * 把指定Date转化为指定格式的字符串日期。<br>
     * 日期格式请参考java.text.SimpleDateFormat的注释。<br>
     * 
     * @param date 日期
     * @param pattern 日期格式
     * @return 字符串日期
     */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 把指定Date转化为指定格式的字符串日期。<br>
     * 日期格式请参考java.text.SimpleDateFormat的注释。<br>
     * 
     * @param date 日期
     * @param pattern 日期格式
     * @param timeZone 时区
     * @return 字符串日期
     */
    public static String format(Date date, String pattern, TimeZone timeZone) {
        return DateFormatUtils.format(date, pattern, timeZone);
    }

    /**
     * 把指定格式的字符串日期转化为Date对象。<br>
     * 日期格式请参考java.text.SimpleDateFormat的注释。<br>
     * 
     * 
     * @param stringDate 字符串日期
     * @param pattern 日期格式
     * @return Date
     * @throws ParseException 字符串日期解析异常
     */
    public static Date parse(String stringDate, String pattern) throws ParseException {
        return org.apache.commons.lang3.time.DateUtils.parseDate(stringDate, pattern);
    }

    /**
     * 把指定格式的字符串日期转化为Date对象。<br>
     * 日期格式请参考java.text.SimpleDateFormat的注释。<br>
     * 
     * @param stringDate 字符串日期
     * @param pattern 日期格式
     * @param defaultDate 默认值
     * @return Date
     */
    public static Date parse(String stringDate, String pattern, Date defaultDate) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(stringDate, pattern);
        } catch (ParseException e) {
            return defaultDate;
        }
    }

}
