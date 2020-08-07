/**
 * 
 */
package com.sunsharing.xshare.framework.web.rest.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunsharing.xshare.framework.core.collection.ArrayUtils;
import com.sunsharing.xshare.framework.core.lang.StringUtils;
import com.sunsharing.xshare.framework.core.message.MessageUtils;
import com.sunsharing.xshare.framework.web.rest.client.RestClient;
import com.sunsharing.xshare.framework.web.rest.exception.RestApiConfigurationException;

/**
 * RestApi拦截器。
 *
 * @author Kison 2017年4月10日
 */
public class RestApiInterceptor implements MethodInterceptor {

    /** Rest客户端 */
    private final RestClient restClient;

    /** 被调用的RestApi的服务器地址 */
    private final String serverUrl;

    /** 被调用的RestApi方法 */
    private Method restApiMethod;

    /**
     * 创建RestApi拦截器。
     * 
     * @param restClient Rest客户端
     * @param serverUrl 被调用的RestApi的服务器地址
     */
    public RestApiInterceptor(RestClient restClient, String serverUrl) {
        this.restClient = restClient;
        this.serverUrl = serverUrl;
    }

    @Override
    public Object intercept(Object enhancedObject, Method originalMethod, Object[] arguments, MethodProxy proxiedMethod) throws Throwable {
        this.restApiMethod = originalMethod;
        return invokeRestApiMethod(originalMethod, arguments);
    }

    private Object invokeRestApiMethod(Method restApiMethod, Object[] parameterValues) {
        HttpMethod httpMethod = getHttpMethod(restApiMethod);
        if (httpMethod == HttpMethod.GET) {
            return invokeGetMethod(restApiMethod, parameterValues);
        } else if (httpMethod == HttpMethod.POST) {
            return invokePostMethod(restApiMethod, parameterValues);
        }
        return null;
    }

    private Object invokeGetMethod(Method restApiMethod, Object[] parameterValues) {
        String url = getUrl(restApiMethod, parameterValues); // http://localhost:8080/xshare-server/user/list?userName=abc
        Type responseType = restApiMethod.getGenericReturnType();
        Map<String, String> pathVariables = getPathVariables(restApiMethod, parameterValues);
        return restClient.getForObject(url, responseType, pathVariables);
    }

    private Object invokePostMethod(Method restApiMethod, Object[] parameterValues) {
        String url = getUrl(restApiMethod);
        Request request = getRequestBody(restApiMethod, parameterValues);
        Object requestValue = request.getValue();
        Type requestType = request.getType();
        Type responseType = restApiMethod.getGenericReturnType();
        Map<String, String> pathVariables = getPathVariables(restApiMethod, parameterValues);
        return restClient.postForObject(url, requestValue, requestType, responseType, pathVariables);
    }

    private HttpMethod getHttpMethod(Method restApiMethod) {
        GetMapping getMapping = restApiMethod.getAnnotation(GetMapping.class);
        if (getMapping != null) {
            return HttpMethod.GET;
        }

        PostMapping postMapping = restApiMethod.getAnnotation(PostMapping.class);
        if (postMapping != null) {
            return HttpMethod.POST;
        }

        PatchMapping patchMapping = restApiMethod.getAnnotation(PatchMapping.class);
        if (patchMapping != null) {
            return HttpMethod.PATCH;
        }

        PutMapping putMapping = restApiMethod.getAnnotation(PutMapping.class);
        if (putMapping != null) {
            return HttpMethod.PUT;
        }

        DeleteMapping deleteMapping = restApiMethod.getAnnotation(DeleteMapping.class);
        if (deleteMapping != null) {
            return HttpMethod.DELETE;
        }

        RequestMapping requestMapping = getRequestMapping(restApiMethod);
        return getHttpMethod(requestMapping);
    }

    private HttpMethod getHttpMethod(RequestMapping requestMapping) {
        RequestMethod[] requestMethods = requestMapping.method();
        if (ArrayUtils.isEmpty(requestMethods)) {
            throwRestApiConfigurationException("{0}类中的{1}方法的@RequestMapping注解没有配置method属性。");
        }
        RequestMethod requestMethod = requestMethods[0];
        return HttpMethod.resolve(requestMethod.name());
    }

    private String getUrl(Method restApiMethod, Object[] parameterValues) {
        String url = getUrl(restApiMethod); // http://localhost:8080/xshare-server/user/list
        Map<String, String> requestParameters = getRequestParameters(restApiMethod, parameterValues); // userName=abc
        String requestParameter = getRequestParameters(requestParameters); // ?userName=abc
        return url + requestParameter; // http://localhost:8080/xshare-server/user/list?userName=abc
    }

    private String getUrl(Method restApiMethod) {
        GetMapping getMapping = restApiMethod.getAnnotation(GetMapping.class);
        if (getMapping != null) {
            return getUrl(getMapping.value(), getMapping.path());
        }

        PostMapping postMapping = restApiMethod.getAnnotation(PostMapping.class);
        if (postMapping != null) {
            return getUrl(postMapping.value(), postMapping.path());
        }

        PatchMapping patchMapping = restApiMethod.getAnnotation(PatchMapping.class);
        if (patchMapping != null) {
            return getUrl(patchMapping.value(), patchMapping.path());
        }

        PutMapping putMapping = restApiMethod.getAnnotation(PutMapping.class);
        if (putMapping != null) {
            return getUrl(putMapping.value(), putMapping.path());
        }

        DeleteMapping deleteMapping = restApiMethod.getAnnotation(DeleteMapping.class);
        if (deleteMapping != null) {
            return getUrl(deleteMapping.value(), deleteMapping.path());
        }

        RequestMapping requestMapping = getRequestMapping(restApiMethod);
        return getUrl(requestMapping.value(), requestMapping.path());
    }

    private String getUrl(String[] values, String[] paths) {
        String[] mappingPaths = ArrayUtils.isNotEmpty(values) ? values : paths;
        if (ArrayUtils.isEmpty(mappingPaths)) {
            throwRestApiConfigurationException("{0}类中的{1}方法的Mapping注解没有配置value或path属性。");
        }
        return serverUrl + mappingPaths[0];
    }

    private RequestMapping getRequestMapping(Method restApiMethod) {
        RequestMapping requestMapping = restApiMethod.getAnnotation(RequestMapping.class);
        if (requestMapping == null) {
            throwRestApiConfigurationException("{0}类中的{1}方法没有配置Mapping注解。");
        }
        return requestMapping;
    }

    private Map<String, String> getPathVariables(Method restApiMethod, Object[] parameterValues) {
        Map<String, String> pathVariables = new HashMap<>();
        if (ArrayUtils.isNotEmpty(parameterValues)) {
            Parameter[] parameters = restApiMethod.getParameters();
            for (int index = 0; index < parameters.length; index++) {
                Parameter parameter = parameters[index];
                PathVariable pathVariable = parameter.getAnnotation(PathVariable.class);
                if (pathVariable != null) {
                    String pathVariableName = StringUtils.isNotEmpty(pathVariable.value()) ? pathVariable.value()
                            : StringUtils.isNotEmpty(pathVariable.name()) ? pathVariable.name() : parameter.getName();
                    String pathVariableValue = String.valueOf(parameterValues[index]);
                    pathVariables.put(pathVariableName, pathVariableValue);
                }
            }
        }
        return pathVariables;
    }

    private Map<String, String> getRequestParameters(Method restApiMethod, Object[] parameterValues) {
        Map<String, String> requestParameters = new HashMap<>();
        if (ArrayUtils.isNotEmpty(parameterValues)) {
            Parameter[] parameters = restApiMethod.getParameters();
            for (int index = 0; index < parameters.length; index++) {
                Parameter parameter = parameters[index];
                RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
                if (requestParam != null) {
                    String requestParameterName = StringUtils.isNotEmpty(requestParam.value()) ? requestParam.value()
                            : StringUtils.isNotEmpty(requestParam.name()) ? requestParam.name() : parameter.getName();
                    String requestParameterValue = String.valueOf(parameterValues[index]);
                    requestParameters.put(requestParameterName, requestParameterValue);
                }
            }
        }
        return requestParameters;
    }

    private String getRequestParameters(Map<String, String> requestParameters) {
        StringBuffer sbRequestParameters = new StringBuffer();
        for (Entry<String, String> requestParameter : requestParameters.entrySet()) {
            char separator = sbRequestParameters.length() == 0 ? '?' : '&';
            String requestParameterName = requestParameter.getKey();
            String requestParameterValue = requestParameter.getValue();
            sbRequestParameters.append(separator).append(requestParameterName).append('=').append(requestParameterValue);
        }
        return sbRequestParameters.toString();
    }

    private Request getRequestBody(Method restApiMethod, Object[] parameterValues) {
        if (ArrayUtils.isNotEmpty(parameterValues)) {
            Parameter[] parameters = restApiMethod.getParameters();
            for (int index = 0; index < parameters.length; index++) {
                Parameter parameter = parameters[index];
                RequestBody requestBody = parameter.getAnnotation(RequestBody.class);
                if (requestBody != null) {
                    Type requestType = parameter.getParameterizedType();
                    Object requestValue = parameterValues[index];
                    return new Request(requestType, requestValue);
                }
            }
        }
        return new Request();
    }

    private void throwRestApiConfigurationException(String pattern) {
        Class<?> restApiClass = restApiMethod.getDeclaringClass();
        String restApiClassName = restApiClass.getName();
        String restApiMethodName = restApiMethod.getName();
        String message = MessageUtils.format(pattern, restApiClassName, restApiMethodName);
        throw new RestApiConfigurationException(message);
    }

    private class Request {

        private Type type;

        private Object value;

        public Request() {

        }

        public Request(Type type, Object value) {
            this.type = type;
            this.value = value;
        }

        public Type getType() {
            return type;
        }

        public Object getValue() {
            return value;
        }

    }

}
