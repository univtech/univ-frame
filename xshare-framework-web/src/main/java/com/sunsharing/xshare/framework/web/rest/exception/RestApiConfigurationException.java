/**
 * 
 */
package com.sunsharing.xshare.framework.web.rest.exception;

import com.sunsharing.xshare.framework.core.exception.UncheckedException;

/**
 * RestApi配置异常。
 *
 * @author Kison 2017年4月16日
 */
public class RestApiConfigurationException extends UncheckedException {

    private static final long serialVersionUID = -1766863168461662560L;

    /**
     * 创建RestApiConfigurationException。
     */
    public RestApiConfigurationException() {
        super();
    }

    /**
     * 创建RestApiConfigurationException。
     * 
     * @param message 异常信息
     */
    public RestApiConfigurationException(String message) {
        super(message);
    }

    /**
     * 创建RestApiConfigurationException。
     * 
     * @param cause 异常原因
     */
    public RestApiConfigurationException(Throwable cause) {
        super(cause);
    }

    /**
     * 创建RestApiConfigurationException。
     * 
     * @param message 异常信息
     * @param cause 异常原因
     */
    public RestApiConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

}
