/**
 * 
 */
package com.sunsharing.xshare.framework.core.lang;

/**
 * 布尔工具类，用于处理：boolean/Boolean。<br>
 * <p>
 *
 * 参考：<br>
 * org.apache.commons.lang3.BooleanUtils<br>
 *
 * @author Kison 2017年3月31日
 */
public class BooleanUtils {

    /**
     * 判断指定表达式是否为true。
     * 
     * @param expression 指定表达式
     * @return 如果指定表达式为true，返回true；否则返回false
     */
    public static boolean isTrue(boolean expression) {
        return org.apache.commons.lang3.BooleanUtils.isTrue(expression);
    }

    /**
     * 判断指定表达式是否为false。
     * 
     * @param expression 指定表达式
     * @return 如果指定表达式为false，返回true；否则返回false
     */
    public static boolean isFalse(boolean expression) {
        return org.apache.commons.lang3.BooleanUtils.isFalse(expression);
    }

}
