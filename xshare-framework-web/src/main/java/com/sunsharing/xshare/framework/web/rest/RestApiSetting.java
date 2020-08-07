/**
 * 
 */
package com.sunsharing.xshare.framework.web.rest;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.sunsharing.xshare.framework.core.assertion.ParameterAssertor;
import com.sunsharing.xshare.framework.core.collection.ArrayUtils;

/**
 * RestApi配置器。
 *
 * @author Kison 2017年4月10日
 */
public class RestApiSetting {

    /** RestApi调用方用户名 */
    private String userName;

    /** RestApi调用方密码 */
    private String password;

    /** RestApi的服务器地址 */
    private final String serverUrl;

    /** RestApi所属的包 */
    private final Set<String> packageNames = new HashSet<>();

    /**
     * 创建RestApi配置器。
     * 
     * @param serverUrl RestApi的服务器地址
     * @param packageNames RestApi所属的包
     */
    public RestApiSetting(String serverUrl, String... packageNames) {
        ParameterAssertor.hasText(serverUrl, "RestApi的服务器地址serverUrl不能为空。");
        ParameterAssertor.isNotEmpty(packageNames, "RestApi所属的包packageNames不能为空。");
        this.serverUrl = serverUrl;
        this.packageNames.addAll(ArrayUtils.toSet(packageNames));
    }

    /**
     * 创建RestApi配置器。
     * 
     * @param serverUrl RestApi的服务器地址
     * @param packageNames RestApi所属的包
     */
    public RestApiSetting(String serverUrl, Collection<String> packageNames) {
        ParameterAssertor.hasText(serverUrl, "RestApi的服务器地址serverUrl不能为空。");
        ParameterAssertor.isNotEmpty(packageNames, "RestApi所属的包packageNames不能为空。");
        this.serverUrl = serverUrl;
        this.packageNames.addAll(packageNames);
    }

    /**
     * 创建RestApi配置器。
     * 
     * @param userName RestApi调用方用户名
     * @param password RestApi调用方密码
     * @param serverUrl RestApi的服务器地址
     * @param packageNames RestApi所属的包
     */
    public RestApiSetting(String userName, String password, String serverUrl, String... packageNames) {
        ParameterAssertor.hasText(userName, "RestApi调用方用户名userName不能为空。");
        ParameterAssertor.hasText(password, "RestApi调用方密码password不能为空。");
        ParameterAssertor.hasText(serverUrl, "RestApi的服务器地址serverUrl不能为空。");
        ParameterAssertor.isNotEmpty(packageNames, "RestApi所属的包packageNames不能为空。");
        this.userName = userName;
        this.password = password;
        this.serverUrl = serverUrl;
        this.packageNames.addAll(ArrayUtils.toSet(packageNames));
    }

    /**
     * 创建RestApi配置器。
     * 
     * @param userName RestApi调用方用户名
     * @param password RestApi调用方密码
     * @param serverUrl RestApi的服务器地址
     * @param packageNames RestApi所属的包
     */
    public RestApiSetting(String userName, String password, String serverUrl, Collection<String> packageNames) {
        ParameterAssertor.hasText(userName, "RestApi调用方用户名userName不能为空。");
        ParameterAssertor.hasText(password, "RestApi调用方密码password不能为空。");
        ParameterAssertor.hasText(serverUrl, "RestApi的服务器地址serverUrl不能为空。");
        ParameterAssertor.isNotEmpty(packageNames, "RestApi所属的包packageNames不能为空。");
        this.userName = userName;
        this.password = password;
        this.serverUrl = serverUrl;
        this.packageNames.addAll(packageNames);
    }

    /**
     * 获取RestApi调用方用户名。
     * 
     * @return RestApi调用方用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 获取RestApi调用方密码。
     * 
     * @return RestApi调用方密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 获取RestApi的服务器地址。
     * 
     * @return RestApi的服务器地址
     */
    public String getServerUrl() {
        return serverUrl;
    }

    /**
     * 获取RestApi所属的包。
     * 
     * @return RestApi所属的包
     */
    public Set<String> getPackageNames() {
        return packageNames;
    }

}
