/**
 * 
 */
package com.sunsharing.xshare.framework.core.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.sunsharing.xshare.framework.core.assertion.ParameterAssertor;

/**
 * 资源加载器。<br>
 * <p>
 * 
 * 参考：<br>
 * org.springframework.core.io.ResourceLoader<br>
 * org.springframework.core.io.support.ResourcePatternResolver<br>
 * org.springframework.core.io.support.PathMatchingResourcePatternResolver<br>
 * 
 * @author Kison - 2016年9月28日
 */
public class ResourceLoader {

    /** 资源的位置模式 */
    private final Collection<String> locationPatterns;

    /**
     * 创建资源加载器。<br>
     * 支持以classpath:或classpath*:为前缀，并且使用*或?通配符的资源位置模式。
     * 
     * @param locationPatterns 资源的位置模式
     */
    public ResourceLoader(String... locationPatterns) {
        ParameterAssertor.isNotEmpty(locationPatterns, "资源的位置模式{}不能为空。", "locationPatterns");
        this.locationPatterns = Arrays.asList(locationPatterns);
    }

    /**
     * 创建资源加载器。<br>
     * 支持以classpath:或classpath*:为前缀，并且使用*或?通配符的资源位置模式。
     * 
     * @param locationPatterns 资源的位置模式
     */
    public ResourceLoader(Collection<String> locationPatterns) {
        ParameterAssertor.isNotEmpty(locationPatterns, "资源的位置模式{}不能为空。", "locationPatterns");
        this.locationPatterns = locationPatterns;
    }

    /**
     * 加载资源。
     * 
     * @return Resource列表
     * @throws IOException IO异常
     */
    public List<Resource> loadResources() throws IOException {
        List<Resource> resources = new ArrayList<>();
        for (String locationPattern : locationPatterns) {
            resources.addAll(Arrays.asList(loadResources(locationPattern)));
        }
        return resources;
    }

    /**
     * 根据指定的资源位置模式，加载资源。
     * 
     * @param locationPattern 资源的位置模式
     * @return Resource数组
     * @throws IOException IO异常
     */
    private Resource[] loadResources(String locationPattern) throws IOException {
        return getResourcePatternResolver().getResources(locationPattern);
    }

    /**
     * 获取PathMatchingResourcePatternResolver类型的资源模式解析器。
     * 
     * @return ResourcePatternResolver对象
     */
    private ResourcePatternResolver getResourcePatternResolver() {
        return new PathMatchingResourcePatternResolver();
    }

}
