/**
 * 
 */
package com.sunsharing.xshare.framework.core.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 日志记录器管理器。<br>
 *
 * @author Kison 2017年3月31日
 */
public class LoggerManager {

    /**
     * 创建日志记录器适配器，用于包装org.apache.logging.log4j.Logger对象。
     * 
     * @param clazz 类对象
     * @return 日志记录器适配器
     */
    public static LoggerAdapter getLogger(Class<?> clazz) {
        Logger logger = LogManager.getLogger(clazz);
        return new LoggerAdapter(logger);
    }

}
