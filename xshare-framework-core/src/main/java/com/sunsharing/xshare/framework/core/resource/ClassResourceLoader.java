/**
 * 
 */
package com.sunsharing.xshare.framework.core.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import com.sunsharing.xshare.framework.core.assertion.ParameterAssertor;

/**
 * Class资源加载器。<br>
 * <p>
 *
 * 参考：<br>
 * org.springframework.core.type.ClassMetadata<br>
 * org.springframework.core.type.AnnotationMetadata<br>
 * org.springframework.core.type.classreading.MetadataReader<br>
 * org.springframework.core.type.classreading.MetadataReaderFactory<br>
 * org.springframework.core.type.classreading.CachingMetadataReaderFactory<br>
 *
 * @author Kison 2017年3月31日
 */
public class ClassResourceLoader {

    /** 包名 */
    private final Collection<String> packageNames;

    /** Class匹配器 */
    private ClassMatcher classMatcher;

    /**
     * 创建类加载器对象。
     * 
     * @param packageNames 包名
     */
    public ClassResourceLoader(String... packageNames) {
        ParameterAssertor.isNotEmpty(packageNames, "包名{}不能为空。", "packageNames");
        this.packageNames = Arrays.asList(packageNames);
    }

    /**
     * 创建类加载器对象。
     * 
     * @param packageNames 包名
     */
    public ClassResourceLoader(Collection<String> packageNames) {
        ParameterAssertor.isNotEmpty(packageNames, "包名{}不能为空。", "packageNames");
        this.packageNames = packageNames;
    }

    /**
     * 加载指定包名及其子包中的所有Class。
     * 
     * @return Class对象列表
     * @throws IOException IO异常
     * @throws ClassNotFoundException 类找不到异常
     */
    public List<Class<?>> loadClasses() throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        MetadataReaderFactory metadataReaderFactory = getMetadataReaderFactory();
        for (Resource resource : loadClassResources()) {
            loadClass(classes, metadataReaderFactory.getMetadataReader(resource));
        }
        return classes;
    }

    /**
     * 根据指定元数据读取器，加载Class对象。
     *
     * @param classes Class对象列表
     * @param metadataReader 元数据读取器
     * @throws ClassNotFoundException 类找不到异常
     */
    private void loadClass(List<Class<?>> classes, MetadataReader metadataReader) throws ClassNotFoundException {
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        if (classMatcher == null || classMatcher.match(classMetadata, annotationMetadata)) {
            classes.add(loadClass(classMetadata.getClassName()));
        }
    }

    /**
     * 加载指定类名的Class对象。
     * 
     * @param className 类名
     * @return Class对象
     * @throws ClassNotFoundException 类找不到异常
     */
    private Class<?> loadClass(String className) throws ClassNotFoundException {
        return getClass().getClassLoader().loadClass(className);
    }

    /**
     * 加载指定包名及其子包中的所有Class文件。
     * 
     * @return Resource对象列表
     * @throws IOException IO异常
     */
    private List<Resource> loadClassResources() throws IOException {
        ResourceLoader resourceLoader = new ResourceLoader(buildClassPathPattern());
        return resourceLoader.loadResources();
    }

    /**
     * 构建指定包名的类路径模式：classpath*:xxx/xxx/xxx&#47;**&#47;*.class。
     * 
     * @return 指定包名的类路径模式：classpath*:xxx/xxx/xxx&#47;**&#47;*.class
     */
    private Set<String> buildClassPathPattern() {
        Set<String> classPathPatterns = new HashSet<>();
        for (String packageName : packageNames) {
            classPathPatterns.add(buildClassPathPattern(packageName));
        }
        return classPathPatterns;
    }

    /**
     * 构建指定包名的类路径模式：classpath*:xxx/xxx/xxx&#47;**&#47;*.class。
     * 
     * @param packageName 包名
     * @return 指定包名的类路径模式：classpath*:xxx/xxx/xxx&#47;**&#47;*.class
     */
    private String buildClassPathPattern(String packageName) {
        return getClassPathPrefix() + getPackagePath(packageName) + getClassPathSuffix();
    }

    /**
     * 创建CachingMetadataReaderFactory类型的元数据读取器工厂对象。
     * 
     * @return MetadataReaderFactory对象
     */
    private MetadataReaderFactory getMetadataReaderFactory() {
        return new CachingMetadataReaderFactory(new PathMatchingResourcePatternResolver());
    }

    /**
     * 获取类路径前缀：classpath*:。
     * 
     * @return 类路径前缀：classpath*:
     */
    private String getClassPathPrefix() {
        return ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX;
    }

    /**
     * 获取类路径后缀：&#47;**&#47;*.class。
     * 
     * @return 类路径后缀：&#47;**&#47;*.class
     */
    private String getClassPathSuffix() {
        return "/**/*.class";
    }

    /**
     * 获取指定包名的路径：xxx/xxx/xxx。
     * 
     * @param packageName 包名
     * @return 包路径：xxx/xxx/xxx
     */
    private String getPackagePath(String packageName) {
        return ClassUtils.convertClassNameToResourcePath(packageName);
    }

    /**
     * 设置Class匹配器。
     * 
     * @param classMatcher Class匹配器
     */
    public void setClassMatcher(ClassMatcher classMatcher) {
        this.classMatcher = classMatcher;
    }

}
