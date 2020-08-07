/**
 * 
 */
package com.sunsharing.xshare.framework.web.mvc.message;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

/**
 * HttpMessageConverter构造器。
 *
 * @author Kison 2018年2月5日
 */
public class HttpMessageConverterBuilder implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public List<HttpMessageConverter<?>> buildHttpMessageConverters() {
        List<HttpMessageConverter<?>> httpMessageConverters = new ArrayList<>();
        httpMessageConverters.add(new ByteArrayHttpMessageConverter());
        httpMessageConverters.add(stringHttpMessageConverter());
        httpMessageConverters.add(new ResourceHttpMessageConverter());
        httpMessageConverters.add(new SourceHttpMessageConverter<Source>());
        httpMessageConverters.add(new AllEncompassingFormHttpMessageConverter());
        httpMessageConverters.add(mappingJackson2HttpMessageConverter());
        httpMessageConverters.add(new Jaxb2RootElementHttpMessageConverter());
        return httpMessageConverters;
    }

    private StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setWriteAcceptCharset(false);
        return stringHttpMessageConverter;
    }

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        AnnotationIntrospector primary = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
        AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
        AnnotationIntrospector annotationIntrospector = AnnotationIntrospector.pair(primary, secondary);

        Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder = Jackson2ObjectMapperBuilder.json();
        jackson2ObjectMapperBuilder.applicationContext(applicationContext);
        jackson2ObjectMapperBuilder.annotationIntrospector(annotationIntrospector);
        jackson2ObjectMapperBuilder.defaultUseWrapper(true);
        ObjectMapper objectMapper = jackson2ObjectMapperBuilder.build();
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
