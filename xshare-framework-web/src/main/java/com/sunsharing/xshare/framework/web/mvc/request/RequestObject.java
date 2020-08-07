/**
 * 
 */
package com.sunsharing.xshare.framework.web.mvc.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 请求对象。
 *
 * @author Kison 2017年4月11日
 */
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestObject<T> extends Request {

    @Valid
    @NotNull(message = "请求的数据对象不能为空")
    @XmlElement(name = "requestData")
    private T requestData; // 请求的数据对象

    public RequestObject() {

    }

    public RequestObject(T requestData) {
        this.requestData = requestData;
    }

    public T getRequestData() {
        return requestData;
    }

    public void setRequestData(T requestData) {
        this.requestData = requestData;
    }

}
