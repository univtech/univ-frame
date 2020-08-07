/**
 * 
 */
package com.sunsharing.xshare.framework.web.mvc.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 响应对象。
 *
 * @author Kison 2017年4月11日
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseArray<T> extends Response {

    @XmlElementWrapper(name = "responseDatas")
    @XmlElement(name = "responseData")
    private T[] responseDatas; // 响应的数据对象

    public ResponseArray() {
        super();
    }

    public ResponseArray(String resultCode, String resultMessage) {
        super(resultCode, resultMessage);
    }

    public ResponseArray(String resultCode, String resultMessage, long costTime) {
        super(resultCode, resultMessage, costTime);
    }

    public ResponseArray(String resultCode, String resultMessage, T[] responseDatas) {
        super(resultCode, resultMessage);
        this.responseDatas = responseDatas;
    }

    public ResponseArray(String resultCode, String resultMessage, long costTime, T[] responseDatas) {
        super(resultCode, resultMessage, costTime);
        this.responseDatas = responseDatas;
    }

    public T[] getResponseDatas() {
        return responseDatas;
    }

    public void setResponseDatas(T[] responseDatas) {
        this.responseDatas = responseDatas;
    }

}
