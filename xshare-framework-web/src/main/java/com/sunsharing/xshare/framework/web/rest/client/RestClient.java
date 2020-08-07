/**
 * 
 */
package com.sunsharing.xshare.framework.web.rest.client;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sunsharing.xshare.framework.core.assertion.ParameterAssertor;
import com.sunsharing.xshare.framework.web.rest.RestApiSetting;

/**
 * Rest客户端，使用RESTful方式调用服务。
 *
 * @author Kison 2017年4月11日
 */
public class RestClient extends RestTemplate {

    /**
     * 创建Rest客户端。
     * 
     * @param httpMessageConverters HTTP报文转换器
     */
    public RestClient(List<HttpMessageConverter<?>> httpMessageConverters) {
        super(httpMessageConverters);
    }

    /**
     * 创建Rest客户端。
     * 
     * @param httpMessageConverters HTTP报文转换器
     * @param restApiSetting RestApi配置对象
     */
    public RestClient(List<HttpMessageConverter<?>> httpMessageConverters, RestApiSetting restApiSetting) {
        super(httpMessageConverters);

        ParameterAssertor.hasText(restApiSetting.getUserName(), "用户名参数userName不能为空。");
        ParameterAssertor.hasText(restApiSetting.getPassword(), "密码参数password不能为空。");

        List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors = new ArrayList<>();
        clientHttpRequestInterceptors.add(new BasicAuthorizationInterceptor(restApiSetting.getUserName(), restApiSetting.getPassword()));
        setInterceptors(clientHttpRequestInterceptors);
    }

    /**
     * 执行HTTP GET请求。
     * 
     * @param <T> 响应类型
     * @param url 请求路径
     * @param responseType 响应类型
     * @param uriVariables URI变量
     * @return 响应对象
     * @throws RestClientException REST客户端异常
     */
    public <T> T getForObject(String url, Type responseType, Map<String, ?> uriVariables) throws RestClientException {
        // 参考：getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables)
        ResponseEntity<T> responseEntity = getForEntity(url, responseType, uriVariables);
        return responseEntity.getBody();
    }

    /**
     * 执行HTTP GET请求。
     * 
     * @param <T> 响应类型
     * @param url 请求路径
     * @param responseType 响应类型
     * @param uriVariables URI变量
     * @return 响应实体
     * @throws RestClientException REST客户端异常
     */
    public <T> ResponseEntity<T> getForEntity(String url, Type responseType, Map<String, ?> uriVariables) throws RestClientException {
        // 参考：getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables)
        RequestEntity<Object> requestEntity = new RequestEntity<>(HttpMethod.GET, null);
        return exchange(url, HttpMethod.GET, requestEntity, responseType, uriVariables);
    }

    /**
     * 执行HTTP POST请求。
     * 
     * @param <T> 响应类型
     * @param url 请求路径
     * @param request 请求对象
     * @param requestType 请求类型
     * @param responseType 响应类型
     * @param uriVariables URI变量
     * @return 响应对象
     * @throws RestClientException REST客户端异常
     */
    public <T> T postForObject(String url, Object request, Type requestType, Type responseType, Map<String, ?> uriVariables) throws RestClientException {
        // 参考：postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables)
        ResponseEntity<T> responseEntity = postForEntity(url, request, requestType, responseType, uriVariables);
        return responseEntity.getBody();
    }

    /**
     * 执行HTTP POST请求。
     * 
     * @param <T> 响应类型
     * @param url 请求路径
     * @param request 请求对象
     * @param requestType 请求类型
     * @param responseType 响应类型
     * @param uriVariables URI变量
     * @return 响应实体
     * @throws RestClientException REST客户端异常
     */
    public <T> ResponseEntity<T> postForEntity(String url, Object request, Type requestType, Type responseType, Map<String, ?> uriVariables) throws RestClientException {
        // 参考：postForEntity(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables)
        RequestEntity<Object> requestEntity = new RequestEntity<>(request, HttpMethod.POST, null, requestType);
        return exchange(url, HttpMethod.POST, requestEntity, responseType, uriVariables);
    }

    /**
     * 执行HTTP PATCH请求。
     * 
     * @param <T> 响应类型
     * @param url 请求路径
     * @param request 请求对象
     * @param requestType 请求类型
     * @param responseType 响应类型
     * @param uriVariables URI变量
     * @return 响应对象
     * @throws RestClientException REST客户端异常
     */
    public <T> T patchForObject(String url, Object request, Type requestType, Type responseType, Map<String, ?> uriVariables) throws RestClientException {
        // 参考：patchForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables)
        ResponseEntity<T> responseEntity = patchForEntity(url, request, requestType, responseType, uriVariables);
        return responseEntity.getBody();
    }

    /**
     * 执行HTTP PATCH请求。
     * 
     * @param <T> 响应类型
     * @param url 请求路径
     * @param request 请求对象
     * @param requestType 请求类型
     * @param responseType 响应类型
     * @param uriVariables URI变量
     * @return 响应实体
     * @throws RestClientException REST客户端异常
     */
    public <T> ResponseEntity<T> patchForEntity(String url, Object request, Type requestType, Type responseType, Map<String, ?> uriVariables) throws RestClientException {
        // 参考：patchForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables)
        RequestEntity<Object> requestEntity = new RequestEntity<>(request, HttpMethod.PATCH, null, requestType);
        return exchange(url, HttpMethod.PATCH, requestEntity, responseType, uriVariables);
    }

    /**
     * 执行HTTP PUT请求。
     * 
     * @param url 请求路径
     * @param request 请求对象
     * @param requestType 请求类型
     * @param uriVariables URI变量
     * @throws RestClientException REST客户端异常
     */
    public void put(String url, Object request, Type requestType, Map<String, ?> uriVariables) throws RestClientException {
        // 参考：put(String url, Object request, Map<String, ?> uriVariables)
        RequestEntity<Object> requestEntity = new RequestEntity<>(request, HttpMethod.PUT, null, requestType);
        exchange(url, HttpMethod.PUT, requestEntity, uriVariables);
    }

    /**
     * 执行HTTP请求。
     * 
     * @param <T> 响应类型
     * @param url 请求路径
     * @param method 请求方法
     * @param requestEntity 请求实体
     * @param responseType 响应类型
     * @param uriVariables URI变量
     * @return 响应实体
     * @throws RestClientException REST客户端异常
     */
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, RequestEntity<?> requestEntity, Type responseType, Map<String, ?> uriVariables) throws RestClientException {
        // 参考：exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables)
        RequestCallback requestCallback = httpEntityCallback(requestEntity, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = responseEntityExtractor(responseType);
        return execute(url, method, requestCallback, responseExtractor, uriVariables);
    }

    /**
     * 执行HTTP请求。
     * 
     * @param url 请求路径
     * @param method 请求方法
     * @param requestEntity 请求实体
     * @param uriVariables URI变量
     * @return 响应头部
     * @throws RestClientException REST客户端异常
     */
    public HttpHeaders exchange(String url, HttpMethod method, RequestEntity<?> requestEntity, Map<String, ?> uriVariables) throws RestClientException {
        // 参考：exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables)
        RequestCallback requestCallback = httpEntityCallback(requestEntity);
        ResponseExtractor<HttpHeaders> responseExtractor = headersExtractor();
        return execute(url, method, requestCallback, responseExtractor, uriVariables);
    }

}
