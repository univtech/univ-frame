/**
 * 
 */
package com.sunsharing.xshare.framework.web.mvc.request;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 请求对象。
 *
 * @author Kison 2017年4月11日
 */
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestArray<T> extends Request {

    @Valid
    @NotEmpty(message = "请求的数据对象不能为空")
    @XmlElementWrapper(name = "requestDatas")
    @XmlElement(name = "requestData")
    private T[] requestDatas; // 请求的数据对象

    public RequestArray() {

    }

    public RequestArray(T[] requestDatas) {
        this.requestDatas = requestDatas;
    }

    public T[] getRequestDatas() {
        return requestDatas;
    }

    public void setRequestDatas(T[] requestDatas) {
        this.requestDatas = requestDatas;
    }

}
