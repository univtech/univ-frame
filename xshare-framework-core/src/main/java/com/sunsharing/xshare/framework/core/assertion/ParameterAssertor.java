/**
 * 
 */
package com.sunsharing.xshare.framework.core.assertion;

import java.util.Collection;
import java.util.Map;

import com.sunsharing.xshare.framework.core.collection.ArrayUtils;
import com.sunsharing.xshare.framework.core.collection.CollectionUtils;
import com.sunsharing.xshare.framework.core.collection.MapUtils;
import com.sunsharing.xshare.framework.core.lang.BooleanUtils;
import com.sunsharing.xshare.framework.core.lang.StringUtils;
import com.sunsharing.xshare.framework.core.message.MessageUtils;

/**
 * 参数断言器。<br>
 * 抛出java.lang.IllegalArgumentException异常。<br>
 * <p>
 * 
 * 参考：<br>
 * org.springframework.util.Assert<br>
 *
 * @author Kison 2017年3月27日
 */
public class ParameterAssertor {

    /**
     * 判断指定对象为null。<br>
     * 如果指定对象不为null，抛出IllegalArgumentException异常。<br>
     * 
     * @param object 指定对象
     * @param message 支持message={0}或message={}格式的异常信息
     * @param parameters 异常信息中的参数值
     */
    public static void isNull(Object object, String message, Object... parameters) {
        if (object != null) {
            throwIllegalArgumentException(message, parameters);
        }
    }

    /**
     * 判断指定字符串为空。<br>
     * 如果指定字符串不为空，抛出IllegalArgumentException异常。<br>
     * 
     * @param string 指定字符串
     * @param message 支持message={0}或message={}格式的异常信息
     * @param parameters 异常信息中的参数值
     */
    public static void isEmpty(String string, String message, Object... parameters) {
        if (StringUtils.isNotEmpty(string)) {
            throwIllegalArgumentException(message, parameters);
        }
    }

    /**
     * 判断指定数组为空。<br>
     * 如果指定数组不为空，抛出IllegalArgumentException异常。<br>
     * 
     * @param array 指定数组
     * @param message 支持message={0}或message={}格式的异常信息
     * @param parameters 异常信息中的参数值
     */
    public static void isEmpty(Object[] array, String message, Object... parameters) {
        if (ArrayUtils.isNotEmpty(array)) {
            throwIllegalArgumentException(message, parameters);
        }
    }

    /**
     * 判断指定集合为空。<br>
     * 如果指定集合不为空，抛出IllegalArgumentException异常。<br>
     * 
     * @param collection 指定集合
     * @param message 支持message={0}或message={}格式的异常信息
     * @param parameters 异常信息中的参数值
     */
    public static void isEmpty(Collection<?> collection, String message, Object... parameters) {
        if (CollectionUtils.isNotEmpty(collection)) {
            throwIllegalArgumentException(message, parameters);
        }
    }

    /**
     * 判断指定Map为空。<br>
     * 如果指定Map不为空，抛出IllegalArgumentException异常。<br>
     * 
     * @param map 指定Map
     * @param message 支持message={0}或message={}格式的异常信息
     * @param parameters 异常信息中的参数值
     */
    public static void isEmpty(Map<?, ?> map, String message, Object... parameters) {
        if (MapUtils.isNotEmpty(map)) {
            throwIllegalArgumentException(message, parameters);
        }
    }

    /**
     * 判断指定对象不为null。<br>
     * 如果指定对象为null，抛出IllegalArgumentException异常。<br>
     * 
     * @param object 指定对象
     * @param message 支持message={0}或message={}格式的异常信息
     * @param parameters 异常信息中的参数值
     */
    public static void isNotNull(Object object, String message, Object... parameters) {
        if (object == null) {
            throwIllegalArgumentException(message, parameters);
        }
    }

    /**
     * 判断指定字符串不为空。<br>
     * 如果指定字符串为空，抛出IllegalArgumentException异常。<br>
     * 
     * @param string 指定字符串
     * @param message 支持message={0}或message={}格式的异常信息
     * @param parameters 异常信息中的参数值
     */
    public static void isNotEmpty(String string, String message, Object... parameters) {
        if (StringUtils.isEmpty(string)) {
            throwIllegalArgumentException(message, parameters);
        }
    }

    /**
     * 判断指定数组不为空。<br>
     * 如果指定数组为空，抛出IllegalArgumentException异常。<br>
     * 
     * @param array 指定数组
     * @param message 支持message={0}或message={}格式的异常信息
     * @param parameters 异常信息中的参数值
     */
    public static void isNotEmpty(Object[] array, String message, Object... parameters) {
        if (ArrayUtils.isEmpty(array)) {
            throwIllegalArgumentException(message, parameters);
        }
    }

    /**
     * 判断指定集合不为空。<br>
     * 如果指定集合为空，抛出IllegalArgumentException异常。<br>
     * 
     * @param collection 指定集合
     * @param message 支持message={0}或message={}格式的异常信息
     * @param parameters 异常信息中的参数值
     */
    public static void isNotEmpty(Collection<?> collection, String message, Object... parameters) {
        if (CollectionUtils.isEmpty(collection)) {
            throwIllegalArgumentException(message, parameters);
        }
    }

    /**
     * 判断指定Map不为空。<br>
     * 如果指定Map为空，抛出IllegalArgumentException异常。<br>
     * 
     * @param map 指定Map
     * @param message 支持message={0}或message={}格式的异常信息
     * @param parameters 异常信息中的参数值
     */
    public static void isNotEmpty(Map<?, ?> map, String message, Object... parameters) {
        if (MapUtils.isEmpty(map)) {
            throwIllegalArgumentException(message, parameters);
        }
    }

    /**
     * 判断指定表达式为true。<br>
     * 如果指定表达式为false，抛出IllegalArgumentException异常。<br>
     * 
     * @param expression 指定表达式
     * @param message 支持message={0}或message={}格式的异常信息
     * @param parameters 异常信息中的参数值
     */
    public static void isTrue(boolean expression, String message, Object... parameters) {
        if (BooleanUtils.isFalse(expression)) {
            throwIllegalArgumentException(message, parameters);
        }
    }

    /**
     * 判断指定表达式为false。<br>
     * 如果指定表达式为true，抛出IllegalArgumentException异常。<br>
     * 
     * @param expression 指定表达式
     * @param message 支持message={0}或message={}格式的异常信息
     * @param parameters 异常信息中的参数值
     */
    public static void isFalse(boolean expression, String message, Object... parameters) {
        if (BooleanUtils.isTrue(expression)) {
            throwIllegalArgumentException(message, parameters);
        }
    }

    /**
     * 判断指定字符串中包含非空字符。<br>
     * 如果指定字符串中不包含非空字符，抛出IllegalArgumentException异常。<br>
     * 
     * @param string 指定字符串
     * @param message 支持message={0}或message={}格式的异常信息
     * @param parameters 异常信息中的参数值
     */
    public static void hasText(String string, String message, Object... parameters) {
        if (StringUtils.hasNoText(string)) {
            throwIllegalArgumentException(message, parameters);
        }
    }

    /**
     * 判断指定字符串中不包含非空字符。<br>
     * 如果指定字符串中包含非空字符，抛出IllegalArgumentException异常。<br>
     * 
     * @param string 指定字符串
     * @param message 支持message={0}或message={}格式的异常信息
     * @param parameters 异常信息中的参数值
     */
    public static void hasNoText(String string, String message, Object... parameters) {
        if (StringUtils.hasText(string)) {
            throwIllegalArgumentException(message, parameters);
        }
    }

    /**
     * 抛出IllegalArgumentException异常。<br>
     * 
     * @param message 支持message={0}或message={}格式的异常信息
     * @param parameters 异常信息中的参数值
     */
    private static void throwIllegalArgumentException(String message, Object... parameters) {
        throw new IllegalArgumentException(MessageUtils.format(message, parameters));
    }

}
