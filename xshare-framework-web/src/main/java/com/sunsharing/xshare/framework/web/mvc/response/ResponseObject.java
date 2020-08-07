/**
 * 
 */
package com.sunsharing.xshare.framework.web.mvc.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 响应对象。
 *
 * @author Kison 2017年4月11日
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseObject<T> extends Response {

    @XmlElement(name = "responseData")
    private T responseData; // 响应的数据对象

    public ResponseObject() {
        super();
    }

    public ResponseObject(String resultCode, String resultMessage) {
        super(resultCode, resultMessage);
    }

    public ResponseObject(String resultCode, String resultMessage, long costTime) {
        super(resultCode, resultMessage, costTime);
    }

    public ResponseObject(String resultCode, String resultMessage, T responseData) {
        super(resultCode, resultMessage);
        this.responseData = responseData;
    }

    public ResponseObject(String resultCode, String resultMessage, long costTime, T responseData) {
        super(resultCode, resultMessage, costTime);
        this.responseData = responseData;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

}
