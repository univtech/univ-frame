/**
 * 
 */
package com.sunsharing.xshare.framework.core.resource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.sunsharing.xshare.framework.core.assertion.ParameterAssertor;

/**
 * *.properties文件加载器。<br>
 * <p>
 *
 * 参考：<br>
 * org.springframework.core.io.support.PropertiesLoaderUtils<br>
 * org.springframework.core.io.support.PropertiesLoaderSupport<br>
 * org.springframework.util.PropertiesPersister<br>
 * org.springframework.util.DefaultPropertiesPersister<br>
 *
 * @author Kison 2017年3月31日
 */
public class PropertiesResourceLoader {

    /** *.properties文件的位置模式 */
    private final Collection<String> locationPatterns;

    /** *.properties文件的字符集，默认为UTF-8 */
    private Charset charset = StandardCharsets.UTF_8;

    /**
     * 创建*.properties文件加载器。<br>
     * 支持以classpath:或classpath*:为前缀，并且使用*或?通配符的*.properties文件位置模式。
     * 
     * @param locationPatterns *.properties文件的位置模式
     */
    public PropertiesResourceLoader(String... locationPatterns) {
        ParameterAssertor.isNotEmpty(locationPatterns, "*.properties文件的位置模式{}不能为空。", "locationPatterns");
        this.locationPatterns = Arrays.asList(locationPatterns);
    }

    /**
     * 创建*.properties文件加载器。<br>
     * 支持以classpath:或classpath*:为前缀，并且使用*或?通配符的*.properties文件位置模式。
     * 
     * @param locationPatterns *.properties文件的位置模式
     */
    public PropertiesResourceLoader(Collection<String> locationPatterns) {
        ParameterAssertor.isNotEmpty(locationPatterns, "*.properties文件的位置模式{}不能为空。", "locationPatterns");
        this.locationPatterns = locationPatterns;
    }

    /**
     * 加载*.properties文件。
     * 
     * @return Properties对象
     * @throws IOException IO异常
     */
    public Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        for (Resource resource : loadResources()) {
            PropertiesLoaderUtils.fillProperties(properties, getEncodedResource(resource));
        }
        return properties;
    }

    /**
     * 加载*.properties文件。
     * 
     * @return Resource对象列表
     * @throws IOException IO异常
     */
    private List<Resource> loadResources() throws IOException {
        ResourceLoader resourceLoader = new ResourceLoader(locationPatterns);
        return resourceLoader.loadResources();
    }

    /**
     * 创建指定字符集编码的资源对象。
     * 
     * @param resource Resource对象
     * @return EncodedResource对象
     */
    private EncodedResource getEncodedResource(Resource resource) {
        return new EncodedResource(resource, charset);
    }

    /**
     * 设置字符集，例如UTF-8。
     * 
     * @param charset 字符集
     */
    public void setCharset(String charset) {
        this.charset = Charset.forName(charset);
    }

    /**
     * 设置字符集，例如StandardCharsets.UTF_8。
     * 
     * @param charset 字符集
     */
    public void setCharset(Charset charset) {
        this.charset = charset;
    }

}
