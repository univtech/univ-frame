/**
 * 
 */
package com.sunsharing.xshare.framework.web.mvc.request;

import java.util.HashSet;
import java.util.Set;

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
public class RequestSet<T> extends Request {

    @Valid
    @NotEmpty(message = "请求的数据对象不能为空")
    @XmlElementWrapper(name = "requestDatas")
    @XmlElement(name = "requestData")
    private Set<T> requestDatas = new HashSet<>(); // 请求的数据对象

    public RequestSet() {

    }

    public RequestSet(Set<T> requestDatas) {
        this.requestDatas = requestDatas;
    }

    public Set<T> getRequestDatas() {
        return requestDatas;
    }

    public void setRequestDatas(Set<T> requestDatas) {
        this.requestDatas = requestDatas;
    }

}
