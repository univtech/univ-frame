/**
 * 
 */
package com.sunsharing.xshare.framework.web.mvc.response;

import java.util.HashSet;
import java.util.Set;

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
public class ResponseSet<T> extends Response {

    @XmlElementWrapper(name = "responseDatas")
    @XmlElement(name = "responseData")
    private Set<T> responseDatas = new HashSet<>(); // 响应的数据对象

    public ResponseSet() {
        super();
    }

    public ResponseSet(String resultCode, String resultMessage) {
        super(resultCode, resultMessage);
    }

    public ResponseSet(String resultCode, String resultMessage, long costTime) {
        super(resultCode, resultMessage, costTime);
    }

    public ResponseSet(String resultCode, String resultMessage, Set<T> responseDatas) {
        super(resultCode, resultMessage);
        this.responseDatas = responseDatas;
    }

    public ResponseSet(String resultCode, String resultMessage, long costTime, Set<T> responseDatas) {
        super(resultCode, resultMessage, costTime);
        this.responseDatas = responseDatas;
    }

    public Set<T> getResponseDatas() {
        return responseDatas;
    }

    public void setResponseDatas(Set<T> responseDatas) {
        this.responseDatas = responseDatas;
    }

}
