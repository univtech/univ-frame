/**
 * 
 */
package com.sunsharing.xshare.framework.core.collection;

import java.util.Collection;

/**
 * Collection工具类，用于处理List和Set。<br>
 * <p>
 * 
 * 参考：<br>
 * org.apache.commons.collections4.CollectionUtils<br>
 * org.springframework.util.CollectionUtils<br>
 * 
 * @author Kison 2017年3月9日
 */
public class CollectionUtils {

    /**
     * 判断指定集合是否为空。<br>
     * 
     * @param collection 指定集合
     * @return 如果指定集合为空，返回true；否则返回false
     */
    public static boolean isEmpty(Collection<?> collection) {
        return org.apache.commons.collections4.CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断指定集合是否不为空。<br>
     * 
     * @param collection 指定集合
     * @return 如果指定集合不为空，返回true；否则返回false
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return org.apache.commons.collections4.CollectionUtils.isNotEmpty(collection);
    }

}
