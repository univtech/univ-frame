/**
 * 
 */
package com.sunsharing.xshare.framework.web.security;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security配置对象。
 *
 * @author Kison 2017年6月9日
 */
public class SecuritySetting {

    /** 默认的登录地址：/login */
    public static final String DEFAULT_LOGIN_URL = "/login";

    /** 默认的退出地址：/logout */
    public static final String DEFAULT_LOGOUT_URL = "/logout";

    /** 默认的登录表单的用户名参数：username */
    public static final String DEFAULT_USERNAME_PARAMETER = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

    /** 默认的登录表单的密码参数：password */
    public static final String DEFAULT_PASSWORD_PARAMETER = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;

    /** 登录地址，默认为：/login */
    private String loginUrl = DEFAULT_LOGIN_URL;

    /** 退出地址，默认为：/logout */
    private String logoutUrl = DEFAULT_LOGOUT_URL;

    /** 登录表单的用户名参数，默认为：username */
    private String usernameParameter = DEFAULT_USERNAME_PARAMETER;

    /** 登录表单的密码参数，默认为：password */
    private String passwordParameter = DEFAULT_PASSWORD_PARAMETER;

    /**
     * 获取登录地址，默认为：/login
     * 
     * @return 登录地址，默认为：/login
     */
    public String getLoginUrl() {
        return loginUrl;
    }

    /**
     * 获取登录处理地址，默认为：/login
     * 
     * @return 登录处理地址，默认为：/login
     */
    public String getLoginProcessUrl() {
        return loginUrl;
    }

    /**
     * 获取登录成功后的处理地址，默认为：/login?success
     * 
     * @return 登录成功后的处理地址，默认为：/login?success
     */
    public String getLoginSuccessUrl() {
        return loginUrl + "?success";
    }

    /**
     * 获取登录失败后的处理地址，默认为：/login?error
     * 
     * @return 登录失败后的处理地址，默认为：/login?error
     */
    public String getLoginFailureUrl() {
        return loginUrl + "?error";
    }

    /**
     * 获取退出地址，默认为：/logout
     * 
     * @return 退出地址，默认为：/logout
     */
    public String getLogoutUrl() {
        return logoutUrl;
    }

    /**
     * 获取退出成功后的处理地址，默认为：/login?logout
     * 
     * @return 登录退出后的处理地址，默认为：/login?logout
     */
    public String getLogoutSuccessUrl() {
        return loginUrl + "?logout";
    }

    /**
     * 获取登录表单的用户名参数，默认为：username
     * 
     * @return 登录表单的用户名参数，默认为：username
     */
    public String getUsernameParameter() {
        return usernameParameter;
    }

    /**
     * 获取登录表单的密码参数，默认为：password
     * 
     * @return 登录表单的密码参数，默认为：password
     */
    public String getPasswordParameter() {
        return passwordParameter;
    }

    /**
     * 设置登录地址，默认为：/login。<br>
     * 需要实现处理以下GET请求的控制器：<br>
     * loginUrl<br>
     * loginUrl?success<br>
     * loginUrl?error<br>
     * loginUrl?logout<br>
     * 
     * @param loginUrl 登录地址，默认为：/login
     */
    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    /**
     * 设置获取退出地址，默认为：/logout
     * 
     * @param logoutUrl 获取退出地址，默认为：/logout
     */
    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    /**
     * 设置登录表单的用户名参数，默认为：username
     * 
     * @param usernameParameter 登录表单的用户名参数，默认为：username
     */
    public void setUsernameParameter(String usernameParameter) {
        this.usernameParameter = usernameParameter;
    }

    /**
     * 设置登录表单的密码参数，默认为：password
     * 
     * @param passwordParameter 登录表单的密码参数，默认为：password
     */
    public void setPasswordParameter(String passwordParameter) {
        this.passwordParameter = passwordParameter;
    }

}
