/**
 * 
 */
package com.sunsharing.xshare.framework.core.collection;

import java.util.Map;

/**
 * Map工具类，用于处理Map。<br>
 * <p>
 *
 * 参考：<br>
 * org.apache.commons.collections4.MapUtils<br>
 *
 * @author Kison 2017年3月31日
 */
public class MapUtils {

    /**
     * 判断指定Map是否为空。<br>
     * 
     * @param map 指定Map
     * @return 如果指定Map为空，返回true；否则返回false
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return org.apache.commons.collections4.MapUtils.isEmpty(map);
    }

    /**
     * 判断指定Map是否不为空。<br>
     * 
     * @param map 指定Map
     * @return 如果指定Map不为空，返回true；否则返回false
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return org.apache.commons.collections4.MapUtils.isNotEmpty(map);
    }

}
