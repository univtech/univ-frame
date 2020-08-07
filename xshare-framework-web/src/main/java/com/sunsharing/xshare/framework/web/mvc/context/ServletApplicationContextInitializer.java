/**
 * 
 */
package com.sunsharing.xshare.framework.web.mvc.context;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.sunsharing.xshare.framework.core.log.LoggerAdapter;
import com.sunsharing.xshare.framework.core.log.LoggerManager;
import com.sunsharing.xshare.framework.core.resource.PropertiesResourceLoader;

/**
 * DispatcherServlet的应用上下文初始化器：<br>
 * 1、加载classpath根目录下的所有properties文件，存放在Environment中。
 * 
 * @author Kison - 2016年9月24日
 */
public class ServletApplicationContextInitializer implements ApplicationContextInitializer<AnnotationConfigWebApplicationContext> {

    private final LoggerAdapter logger = LoggerManager.getLogger(getClass());
    
    private static final String CONFIG_RESOURCE_NAME = "CONFIG_RESOURCE";
    
    private static final String CONFIG_RESOURCE_PATTERN = "classpath:*.properties";

    @Override
    public void initialize(AnnotationConfigWebApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        environment.setIgnoreUnresolvableNestedPlaceholders(true);
        environment.getPropertySources().addLast(newPropertiesPropertySource());
    }

    public PropertiesPropertySource newPropertiesPropertySource() {
        // 加载classes目录下的所有*.properties文件内容
        Properties properties = new Properties();
        PropertiesResourceLoader propertiesResourceLoader = new PropertiesResourceLoader(CONFIG_RESOURCE_PATTERN);
        try {
            properties = propertiesResourceLoader.loadProperties();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return new PropertiesPropertySource(CONFIG_RESOURCE_NAME, properties);
    }

}
