/**
 * 
 */
package com.sunsharing.xshare.framework.web.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * RestApi配置器注册器。
 *
 * @author Kison 2017年4月10日
 */
public class RestApiSettingRegistry {

    /** RestApi配置器 */
    private final List<RestApiSetting> restApiSettings = new ArrayList<>();

    /**
     * 注册RestApi配置器。
     * 
     * @param serverUrl RestApi的服务器地址
     * @param packageNames RestApi所属的包
     */
    public void register(String serverUrl, String... packageNames) {
        restApiSettings.add(new RestApiSetting(serverUrl, packageNames));
    }

    /**
     * 注册RestApi配置器。
     * 
     * @param serverUrl RestApi的服务器地址
     * @param packageNames RestApi所属的包
     */
    public void register(String serverUrl, Collection<String> packageNames) {
        restApiSettings.add(new RestApiSetting(serverUrl, packageNames));
    }

    /**
     * 注册RestApi配置器。
     * 
     * @param userName RestApi调用方用户名
     * @param password RestApi调用方密码
     * @param serverUrl RestApi的服务器地址
     * @param packageNames RestApi所属的包
     */
    public void register(String userName, String password, String serverUrl, String... packageNames) {
        restApiSettings.add(new RestApiSetting(userName, password, serverUrl, packageNames));
    }

    /**
     * 注册RestApi配置器。
     * 
     * @param userName RestApi调用方用户名
     * @param password RestApi调用方密码
     * @param serverUrl RestApi的服务器地址
     * @param packageNames RestApi所属的包
     */
    public void register(String userName, String password, String serverUrl, Collection<String> packageNames) {
        restApiSettings.add(new RestApiSetting(userName, password, serverUrl, packageNames));
    }

    /**
     * 获取RestApi配置器。
     * 
     * @return RestApi配置器
     */
    public List<RestApiSetting> getRestApiSettings() {
        return restApiSettings;
    }

}
