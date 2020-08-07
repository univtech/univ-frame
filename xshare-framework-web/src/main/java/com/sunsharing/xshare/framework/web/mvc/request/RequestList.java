/**
 * 
 */
package com.sunsharing.xshare.framework.web.mvc.request;

import java.util.ArrayList;
import java.util.List;

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
public class RequestList<T> extends Request {

    @Valid
    @NotEmpty(message = "请求的数据对象不能为空")
    @XmlElementWrapper(name = "requestDatas")
    @XmlElement(name = "requestData")
    private List<T> requestDatas = new ArrayList<>(); // 请求的数据对象

    public RequestList() {

    }

    public RequestList(List<T> requestDatas) {
        this.requestDatas = requestDatas;
    }

    public List<T> getRequestDatas() {
        return requestDatas;
    }

    public void setRequestDatas(List<T> requestDatas) {
        this.requestDatas = requestDatas;
    }

}
