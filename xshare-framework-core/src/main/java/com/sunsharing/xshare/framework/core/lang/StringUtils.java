/**
 * 
 */
package com.sunsharing.xshare.framework.core.lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类，用于处理String。<br>
 * <p>
 *
 * 参考：<br>
 * org.springframework.util.StringUtils<br>
 * org.apache.commons.lang3.StringUtils<br>
 *
 * @author Kison 2017年3月31日
 */
public class StringUtils {

    /** ""字符串 */
    public static final String EMPTY_STRING = org.apache.commons.lang3.StringUtils.EMPTY;

    /**
     * 判断指定字符串是否为空。<br>
     * 
     * @param string 指定字符串
     * @return 如果指定字符串为空，返回ture；否则返回false
     */
    public static boolean isEmpty(String string) {
        return org.springframework.util.StringUtils.isEmpty(string);
    }

    /**
     * 判断指定字符串是否不为空。<br>
     * 
     * @param string 指定字符串
     * @return 如果指定字符串不为空，返回ture；否则返回false
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * 判断指定字符串中是否包含非空字符。<br>
     * 
     * @param string 指定字符串
     * @return 如果指定字符串中包含非空字符，返回ture；否则返回false
     */
    public static boolean hasText(String string) {
        return org.springframework.util.StringUtils.hasText(string);
    }

    /**
     * 判断指定字符串中是否不包含非空字符。<br>
     * 
     * @param string 指定字符串
     * @return 如果指定字符串中不包含非空字符，返回ture；否则返回false
     */
    public static boolean hasNoText(String string) {
        return !hasText(string);
    }

    /**
     * 去掉指定字符串首尾的空格。
     * 
     * @param string 指定字符串
     * @return
     */
    public static String trim(String string) {
        return org.apache.commons.lang3.StringUtils.trim(string);
    }

    /**
     * 判断指定字符串是否是合法字符串。<br>
     * 正则表达式请参考java.util.regex.Pattern的注释。<br>
     * 
     * @param string 指定字符串
     * @return 如果指定字符串是合法字符串，返回true；否则返回false
     */
    public static boolean isValid(String string) {
        if (hasText(string)) {
            Pattern pattern = Pattern.compile("^[.\\-,#&\\p{Alnum}\\p{Space}]*$");
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }
        return true;
    }

}
