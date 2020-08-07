/**
 * 
 */
package com.sunsharing.xshare.framework.web.rest.proxy;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;

import com.sunsharing.xshare.framework.core.assertion.ParameterAssertor;
import com.sunsharing.xshare.framework.core.resource.ClassMatcher;
import com.sunsharing.xshare.framework.core.resource.ClassResourceLoader;
import com.sunsharing.xshare.framework.web.rest.RestApi;

/**
 * RestApi加载器。<br>
 *
 * @author Kison 2017年4月11日
 */
public class RestApiLoader {

    /** 包名 */
    private final Collection<String> packageNames;

    /**
     * 创建RestApi类加载器对象。
     * 
     * @param packageNames 包名
     */
    public RestApiLoader(Collection<String> packageNames) {
        ParameterAssertor.isNotEmpty(packageNames, "包名{}不能为空。", "packageNames");
        this.packageNames = packageNames;
    }

    /**
     * 加载指定包名及其子包中的所有RestApi Class。
     * 
     * @return RestApi Class对象列表
     * @throws IOException IO异常
     * @throws ClassNotFoundException 类找不到异常
     */
    public List<Class<?>> loadRestApiClasses() throws IOException, ClassNotFoundException {
        ClassResourceLoader classResourceLoader = new ClassResourceLoader(packageNames);
        classResourceLoader.setClassMatcher(new RestApiClassMatcher());
        return classResourceLoader.loadClasses();
    }

    /**
     * RestApi Class匹配器，用于匹配使用RestApi注解标注的接口。
     */
    private class RestApiClassMatcher implements ClassMatcher {
        @Override
        public boolean match(ClassMetadata classMetadata, AnnotationMetadata annotationMetadata) {
            return classMetadata.isInterface() && annotationMetadata.hasAnnotation(RestApi.class.getName());
        }
    }

}
