/**
 * 
 */
package com.sunsharing.xshare.framework.web.mvc;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sunsharing.xshare.framework.web.mvc.context.ApplicationContextSupport;
import com.sunsharing.xshare.framework.web.mvc.message.HttpMessageConverterBuilder;

/**
 * 默认的的WebMvc配置类。
 * <p>
 * 
 * 参考：<br>
 * 1、EnableWebMvc；<br>
 * 2、WebMvcConfigurer；<br>
 * 3、WebMvcConfigurerAdapter；<br>
 * 4、WebMvcConfigurerComposite；<br>
 * 5、WebMvcConfigurationSupport；<br>
 * 6、DelegatingWebMvcConfiguration。
 * 
 * @author Kison 2017年3月8日
 */
public abstract class DefaultWebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public ApplicationContextSupport applicationContextSupport() {
        return new ApplicationContextSupport();
    }

    @Bean
    public HttpMessageConverterBuilder httpMessageConverterBuilder() {
        return new HttpMessageConverterBuilder();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters) {
        httpMessageConverters.addAll(httpMessageConverterBuilder().buildHttpMessageConverters());
    }

    @Override
    public final void extendMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters) {
        // 不扩展
    }

}
