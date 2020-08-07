/**
 * 
 */
package com.sunsharing.xshare.framework.core.exception;

/**
 * 可以不用处理的运行时异常。<br>
 *
 * @author Kison 2017年3月31日
 */
public class UncheckedException extends RuntimeException {

    private static final long serialVersionUID = -681384156909983044L;

    /**
     * 创建UncheckedException。
     */
    public UncheckedException() {
        super();
    }

    /**
     * 创建UncheckedException。
     * 
     * @param message 异常信息
     */
    public UncheckedException(String message) {
        super(message);
    }

    /**
     * 创建UncheckedException。
     * 
     * @param cause 异常原因
     */
    public UncheckedException(Throwable cause) {
        super(cause);
    }

    /**
     * 创建UncheckedException。
     * 
     * @param message 异常信息
     * @param cause 异常原因
     */
    public UncheckedException(String message, Throwable cause) {
        super(message, cause);
    }

}
