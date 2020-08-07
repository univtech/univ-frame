/**
 * 
 */
package com.sunsharing.xshare.framework.web.rest.proxy;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import com.sunsharing.xshare.framework.web.mvc.context.ApplicationContextSupport;
import com.sunsharing.xshare.framework.web.mvc.message.HttpMessageConverterBuilder;
import com.sunsharing.xshare.framework.web.rest.RestApiSetting;
import com.sunsharing.xshare.framework.web.rest.RestApiSettingRegistry;
import com.sunsharing.xshare.framework.web.rest.client.RestClient;

/**
 * RestApi代理配置类。
 *
 * @author Kison 2017年4月10日
 */
@Configuration
public class RestApiProxyConfiguration {

    @Inject
    private RestApiSettingRegistry restApiSettingRegistry;

    @Inject
    private ApplicationContextSupport applicationContextSupport;
    
    @Inject
    private HttpMessageConverterBuilder httpMessageConverterBuilder;

    @PostConstruct
    public void enableRestApiAutoProxy() throws IOException, ClassNotFoundException {
        Map<RestApiSetting, RestClient> restClientMap = createRestClients(restApiSettingRegistry);
        RestApiProxyFactory restApiProxyFactory = new RestApiProxyFactory(restClientMap);
        Map<String, Object> restApiProxyObjectMap = restApiProxyFactory.createRestApiProxyObjects();
        registerRestApiProxyObjects(restApiProxyObjectMap);
    }

    private Map<RestApiSetting, RestClient> createRestClients(RestApiSettingRegistry restApiSettingRegistry) {
        Map<RestApiSetting, RestClient> restClientMap = new HashMap<>();
        List<HttpMessageConverter<?>> httpMessageConverters = httpMessageConverterBuilder.buildHttpMessageConverters();
        for (RestApiSetting restApiSetting : restApiSettingRegistry.getRestApiSettings()) {
            restClientMap.put(restApiSetting, new RestClient(httpMessageConverters, restApiSetting));
        }
        return restClientMap;
    }

    private void registerRestApiProxyObjects(Map<String, Object> restApiProxyObjectMap) {
        for (Entry<String, Object> restApiProxyObjectEntry : restApiProxyObjectMap.entrySet()) {
            String beanName = restApiProxyObjectEntry.getKey();
            Object singletonBean = restApiProxyObjectEntry.getValue();
            applicationContextSupport.registerSingletonBean(beanName, singletonBean);
        }
    }

}
