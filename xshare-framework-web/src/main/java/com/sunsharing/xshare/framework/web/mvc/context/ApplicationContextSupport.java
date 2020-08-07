/**
 * 
 */
package com.sunsharing.xshare.framework.web.mvc.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Web应用上下文支持类，用于操作Web应用上下文。<br>
 * <p>
 *
 * 参考：<br>
 * 1、ApplicationContextAware；<br>
 * 2、AbstractApplicationContext.getAutowireCapableBeanFactory()；<br>
 * 3、AbstractRefreshableApplicationContext.getBeanFactory()。
 *
 * @author Kison 2017年4月11日
 */
public class ApplicationContextSupport implements ApplicationContextAware {

    /** 基于注解配置的Web应用上下文 */
    private AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext;

    /** 能够自动装配的Bean工厂 */
    private DefaultListableBeanFactory defaultListableBeanFactory;

    /**
     * 从Web应用上下文中获取beanType类型的Bean。
     * 
     * @param <T> Bean类型
     * @param beanType Bean类型，不能为null
     * @return beanType类型的Bean
     * @throws BeansException 如果不能创建beanType类型的Bean，抛出此异常
     */
    public <T> T getBean(Class<T> beanType) throws BeansException {
        return annotationConfigWebApplicationContext.getBean(beanType);
    }

    /**
     * 在Web应用上下文中注册指定的Singleton Bean。
     * 
     * @param beanName Bean名称
     * @param singletonBean Singleton Bean对象
     * @throws IllegalStateException 非法状态异常
     */
    public void registerSingletonBean(String beanName, Object singletonBean) throws IllegalStateException {
        defaultListableBeanFactory.registerSingleton(beanName, singletonBean);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        assertAnnotationConfigWebApplicationContext(applicationContext);
        assertDefaultListableBeanFactory(applicationContext);
        this.annotationConfigWebApplicationContext = (AnnotationConfigWebApplicationContext) applicationContext;
        this.defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
    }

    /**
     * 判断ApplicationContext的类型为AnnotationConfigWebApplicationContext。
     * 
     * @param applicationContext 应用程序上下文对象
     */
    private void assertAnnotationConfigWebApplicationContext(ApplicationContext applicationContext) {
        if (!(applicationContext instanceof AnnotationConfigWebApplicationContext)) {
            throw new BeanNotOfRequiredTypeException("applicationContext", AnnotationConfigWebApplicationContext.class, applicationContext.getClass());
        }
    }

    /**
     * 判断ApplicationContext中的AutowireCapableBeanFactory的类型为DefaultListableBeanFactory。
     * 
     * @param applicationContext 应用程序上下文对象
     */
    private void assertDefaultListableBeanFactory(ApplicationContext applicationContext) {
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        if (!(autowireCapableBeanFactory instanceof DefaultListableBeanFactory)) {
            throw new BeanNotOfRequiredTypeException("applicationContext.getAutowireCapableBeanFactory()", DefaultListableBeanFactory.class, autowireCapableBeanFactory.getClass());
        }
    }

}
