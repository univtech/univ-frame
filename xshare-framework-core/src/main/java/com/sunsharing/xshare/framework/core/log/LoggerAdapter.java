/**
 * 
 */
package com.sunsharing.xshare.framework.core.log;

import org.apache.logging.log4j.Logger;

/**
 * 日志记录器适配器。<br>
 * <p>
 *
 * 参考：<br>
 * org.apache.logging.log4j.Logger<br>
 *
 * @author Kison 2017年3月31日
 */
public class LoggerAdapter {

    /** org.apache.logging.log4j.Logger */
    private final Logger logger;

    /**
     * 创建日志记录器适配器。<br>
     * 
     * @param logger 日志记录器
     */
    public LoggerAdapter(Logger logger) {
        this.logger = logger;
    }

    /**
     * 记录FATAL级别的日志。<br>
     * 日志级别：OFF &gt; FATAL &gt; ERROR &gt; WARN &gt; INFO &gt; DEBUG &gt; TRACE &gt; ALL<br>
     * 
     * @param message 支持message={}格式的日志信息
     * @param parameters 参数值
     */
    public void fatal(String message, Object... parameters) {
        logger.fatal(message, parameters);
    }

    /**
     * 记录FATAL级别的日志。<br>
     * 日志级别：OFF &gt; FATAL &gt; ERROR &gt; WARN &gt; INFO &gt; DEBUG &gt; TRACE &gt; ALL<br>
     * 
     * @param message 日志信息
     * @param cause 异常
     */
    public void fatal(String message, Throwable cause) {
        logger.fatal(message, cause);
    }

    /**
     * 记录ERROR级别的日志。<br>
     * 日志级别：OFF &gt; FATAL &gt; ERROR &gt; WARN &gt; INFO &gt; DEBUG &gt; TRACE &gt; ALL<br>
     * 
     * @param message 支持message={}格式的日志信息
     * @param parameters 参数值
     */
    public void error(String message, Object... parameters) {
        logger.error(message, parameters);
    }

    /**
     * 记录ERROR级别的日志。<br>
     * 日志级别：OFF &gt; FATAL &gt; ERROR &gt; WARN &gt; INFO &gt; DEBUG &gt; TRACE &gt; ALL<br>
     * 
     * @param message 日志信息
     * @param cause 异常
     */
    public void error(String message, Throwable cause) {
        logger.error(message, cause);
    }

    /**
     * 记录WARN级别的日志。<br>
     * 日志级别：OFF &gt; FATAL &gt; ERROR &gt; WARN &gt; INFO &gt; DEBUG &gt; TRACE &gt; ALL<br>
     * 
     * @param message 支持message={}格式的日志信息
     * @param parameters 参数值
     */
    public void warn(String message, Object... parameters) {
        logger.warn(message, parameters);
    }

    /**
     * 记录WARN级别的日志。<br>
     * 日志级别：OFF &gt; FATAL &gt; ERROR &gt; WARN &gt; INFO &gt; DEBUG &gt; TRACE &gt; ALL<br>
     * 
     * @param message 日志信息
     * @param cause 异常
     */
    public void warn(String message, Throwable cause) {
        logger.warn(message, cause);
    }

    /**
     * 记录INFO级别的日志。<br>
     * 日志级别：OFF &gt; FATAL &gt; ERROR &gt; WARN &gt; INFO &gt; DEBUG &gt; TRACE &gt; ALL<br>
     * 
     * @param message 支持message={}格式的日志信息
     * @param parameters 参数值
     */
    public void info(String message, Object... parameters) {
        logger.info(message, parameters);
    }

    /**
     * 记录INFO级别的日志。<br>
     * 日志级别：OFF &gt; FATAL &gt; ERROR &gt; WARN &gt; INFO &gt; DEBUG &gt; TRACE &gt; ALL<br>
     * 
     * @param message 日志信息
     * @param cause 异常
     */
    public void info(String message, Throwable cause) {
        logger.info(message, cause);
    }

    /**
     * 记录DEBUG级别的日志。<br>
     * 日志级别：OFF &gt; FATAL &gt; ERROR &gt; WARN &gt; INFO &gt; DEBUG &gt; TRACE &gt; ALL<br>
     * 
     * @param message 支持message={}格式的日志信息
     * @param parameters 参数值
     */
    public void debug(String message, Object... parameters) {
        logger.debug(message, parameters);
    }

    /**
     * 记录DEBUG级别的日志。<br>
     * 日志级别：OFF &gt; FATAL &gt; ERROR &gt; WARN &gt; INFO &gt; DEBUG &gt; TRACE &gt; ALL<br>
     * 
     * @param message 日志信息
     * @param cause 异常
     */
    public void debug(String message, Throwable cause) {
        logger.debug(message, cause);
    }

    /**
     * 记录TRACE级别的日志。<br>
     * 日志级别：OFF &gt; FATAL &gt; ERROR &gt; WARN &gt; INFO &gt; DEBUG &gt; TRACE &gt; ALL<br>
     * 
     * @param message 支持message={}格式的日志信息
     * @param parameters 参数值
     */
    public void trace(String message, Object... parameters) {
        logger.trace(message, parameters);
    }

    /**
     * 记录TRACE级别的日志。<br>
     * 日志级别：OFF &gt; FATAL &gt; ERROR &gt; WARN &gt; INFO &gt; DEBUG &gt; TRACE &gt; ALL<br>
     * 
     * @param message 日志信息
     * @param cause 异常
     */
    public void trace(String message, Throwable cause) {
        logger.trace(message, cause);
    }

}
