/**
 * 
 */
package com.sunsharing.xshare.framework.core.exception;

/**
 * 必须处理的业务异常。<br>
 *
 * @author Kison 2017年3月27日
 */
public class BusinessException extends Exception {

    private static final long serialVersionUID = -681384156909983044L;

    /**
     * 创建BusinessException。
     */
    public BusinessException() {
        super();
    }

    /**
     * 创建BusinessException。
     * 
     * @param message 异常信息
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * 创建BusinessException。
     * 
     * @param cause 异常原因
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }

    /**
     * 创建BusinessException。
     * 
     * @param message 异常信息
     * @param cause 异常原因
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
