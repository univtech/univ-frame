/**
 * 
 */
package com.sunsharing.xshare.framework.web.rest.proxy;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cglib.proxy.Enhancer;

import com.sunsharing.xshare.framework.core.assertion.ParameterAssertor;
import com.sunsharing.xshare.framework.web.rest.RestApiSetting;
import com.sunsharing.xshare.framework.web.rest.client.RestClient;

/**
 * RestApi代理对象工厂。
 *
 * @author Kison 2017年4月11日
 */
public class RestApiProxyFactory {

    /** Rest客户端Map */
    private final Map<RestApiSetting, RestClient> restClientMap;

    /**
     * 创建RestApi代理对象工厂。
     * 
     * @param restClientMap Rest客户端Map
     */
    public RestApiProxyFactory(Map<RestApiSetting, RestClient> restClientMap) {
        ParameterAssertor.isNotEmpty(restClientMap, "Rest客户端Map restClientMap不能为空。");
        this.restClientMap = restClientMap;
    }

    /**
     * 创建RestApi代理对象。
     * 
     * @return RestApi代理对象
     * @throws IOException IO异常
     * @throws ClassNotFoundException 类找不到异常
     */
    public Map<String, Object> createRestApiProxyObjects() throws IOException, ClassNotFoundException {
        Map<String, Object> restApiProxyObjects = new HashMap<>();
        for (RestApiSetting restApiSetting : restClientMap.keySet()) {
            restApiProxyObjects.putAll(createRestApiProxyObjects(restApiSetting));
        }
        return restApiProxyObjects;
    }

    /**
     * 创建RestApi代理对象。
     * 
     * @param restApiSetting RestApi配置器
     * @return RestApi代理对象
     * @throws IOException IO异常
     * @throws ClassNotFoundException 类找不到异常
     */
    private Map<String, Object> createRestApiProxyObjects(RestApiSetting restApiSetting) throws IOException, ClassNotFoundException {
        Map<String, Object> restApiProxyObjects = new HashMap<>();
        List<Class<?>> restApiClasses = loadRestApiClasses(restApiSetting.getPackageNames());
        for (Class<?> restApiClass : restApiClasses) {
            String simpleClassName = restApiClass.getSimpleName();
            String serverUrl = restApiSetting.getServerUrl();
            RestClient restClient = restClientMap.get(restApiSetting);
            restApiProxyObjects.put(simpleClassName, createRestApiProxyObject(serverUrl, restApiClass, restClient));
        }
        return restApiProxyObjects;
    }

    /**
     * 创建RestApi代理对象。
     * 
     * @param serverUrl RestApi的服务器地址
     * @param restApiClass RestApi类
     * @param restClient Rest客户端
     * @return RestApi代理对象
     */
    private Object createRestApiProxyObject(String serverUrl, Class<?> restApiClass, RestClient restClient) {
        return Enhancer.create(restApiClass, new RestApiInterceptor(restClient, serverUrl));
    }

    /**
     * 加载指定包及其子包中的所有RestApi Class。
     * 
     * @param packageNames RestApi所属的包
     * @return 指定包及其子包中的所有RestApi Class
     * @throws IOException IO异常
     * @throws ClassNotFoundException 类找不到异常
     */
    private List<Class<?>> loadRestApiClasses(Collection<String> packageNames) throws IOException, ClassNotFoundException {
        RestApiLoader restApiLoader = new RestApiLoader(packageNames);
        return restApiLoader.loadRestApiClasses();
    }

}
