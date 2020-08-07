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
public class Response {

    @XmlElement(name = "resultCode")
    private String resultCode; // 结果编码

    @XmlElement(name = "resultMessage")
    private String resultMessage; // 结果信息

    @XmlElement(name = "costTime")
    private long costTime; // 耗时

    public Response() {

    }

    public Response(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public Response(String resultCode, String resultMessage, long costTime) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.costTime = costTime;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

}
