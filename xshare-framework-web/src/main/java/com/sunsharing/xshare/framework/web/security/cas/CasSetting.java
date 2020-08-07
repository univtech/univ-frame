/**
 * 
 */
package com.sunsharing.xshare.framework.web.security.cas;

/**
 * CAS配置对象。
 *
 * @author Kison 2017年6月9日
 */
public class CasSetting {

    /** 默认的CAS登录处理地址：/login/cas */
    public static final String DEFAULT_LOGIN_PROCESS_URL = "/login/cas";

    /** 默认的CAS退出处理地址：/logout/cas */
    public static final String DEFAULT_LOGOUT_PROCESS_URL = "/logout/cas";

    /** 默认的CAS登录地址：/login */
    public static final String DEFAULT_LOGIN_URL = "/login";

    /** 默认的CAS退出地址：/logout */
    public static final String DEFAULT_LOGOUT_URL = "/logout";

    /** CAS客户端地址 */
    private String casClientUrl;

    /** CAS服务端地址 */
    private String casServerUrl;

    /**
     * 获取service地址，示例：http://127.0.0.1:8080/sample/login/cas
     * 
     * @return service地址
     */
    public String getServiceUrl() {
        return casClientUrl + DEFAULT_LOGIN_PROCESS_URL;
    }

    /**
     * 获取CAS Server的登录地址，示例：http://127.0.0.1:8080/cas/login
     * 
     * @return CAS Server的登录地址
     */
    public String getCasLoginUrl() {
        return casServerUrl + DEFAULT_LOGIN_URL;
    }

    /**
     * 获取CAS Server的退出地址，示例：http://127.0.0.1:8080/cas/logout
     * 
     * @return CAS Server的退出地址
     */
    public String getCasLogoutUrl() {
        return casServerUrl + DEFAULT_LOGOUT_URL;
    }

    /**
     * 获取CAS客户端地址，示例：http://127.0.0.1:8080/sample
     * 
     * @return CAS客户端地址
     */
    public String getCasClientUrl() {
        return casClientUrl;
    }

    /**
     * 获取CAS服务端地址，示例：http://127.0.0.1:8080/cas
     * 
     * @return CAS服务端地址
     */
    public String getCasServerUrl() {
        return casServerUrl;
    }

    /**
     * 设置CAS客户端地址，示例：http://127.0.0.1:8080/sample
     * 
     * @param casClientUrl CAS客户端地址
     */
    public void setCasClientUrl(String casClientUrl) {
        this.casClientUrl = casClientUrl;
    }

    /**
     * 设置CAS服务端地址，示例：http://127.0.0.1:8080/cas
     * 
     * @param casServerUrl CAS服务端地址
     */
    public void setCasServerUrl(String casServerUrl) {
        this.casServerUrl = casServerUrl;
    }

}
