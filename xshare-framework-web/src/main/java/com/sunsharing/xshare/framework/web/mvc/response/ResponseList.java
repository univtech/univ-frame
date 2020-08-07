/**
 * 
 */
package com.sunsharing.xshare.framework.web.mvc.response;

import java.util.ArrayList;
import java.util.List;

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
public class ResponseList<T> extends Response {

    @XmlElementWrapper(name = "responseDatas")
    @XmlElement(name = "responseData")
    private List<T> responseDatas = new ArrayList<>(); // 响应的数据对象

    public ResponseList() {
        super();
    }

    public ResponseList(String resultCode, String resultMessage) {
        super(resultCode, resultMessage);
    }

    public ResponseList(String resultCode, String resultMessage, long costTime) {
        super(resultCode, resultMessage, costTime);
    }

    public ResponseList(String resultCode, String resultMessage, List<T> responseDatas) {
        super(resultCode, resultMessage);
        this.responseDatas = responseDatas;
    }

    public ResponseList(String resultCode, String resultMessage, long costTime, List<T> responseDatas) {
        super(resultCode, resultMessage, costTime);
        this.responseDatas = responseDatas;
    }

    public List<T> getResponseDatas() {
        return responseDatas;
    }

    public void setResponseDatas(List<T> responseDatas) {
        this.responseDatas = responseDatas;
    }

}
