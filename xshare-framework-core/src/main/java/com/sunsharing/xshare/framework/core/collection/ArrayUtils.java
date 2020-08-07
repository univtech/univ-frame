/**
 * 
 */
package com.sunsharing.xshare.framework.core.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 数组工具类，用于处理Array。<br>
 * <p>
 * 
 * 参考：<br>
 * org.apache.commons.lang3.ArrayUtils<br>
 *
 * @author Kison 2017年3月9日
 */
public class ArrayUtils {

    /**
     * 判断指定数组是否为空。<br>
     * 
     * @param array 指定数组
     * @return 如果指定数组为空，返回true；否则返回false
     */
    public static boolean isEmpty(Object[] array) {
        return org.apache.commons.lang3.ArrayUtils.isEmpty(array);
    }

    /**
     * 判断指定数组是否不为空。<br>
     * 
     * @param array 指定数组
     * @return 如果指定数组不为空，返回true；否则返回false
     */
    public static boolean isNotEmpty(Object[] array) {
        return org.apache.commons.lang3.ArrayUtils.isNotEmpty(array);
    }

    public static <T> List<T> toList(T[] array) {
        return Arrays.asList(array);
    }

    public static <T> Set<T> toSet(T[] array) {
        Set<T> set = new HashSet<>();
        if (isNotEmpty(array)) {
            for (T element : array) {
                set.add(element);
            }
        }
        return set;
    }

}
