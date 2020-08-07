/**
 * 
 */
package com.sunsharing.xshare.framework.web.mvc;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.EventListener;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.Conventions;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.sunsharing.xshare.framework.core.lang.StringUtils;
import com.sunsharing.xshare.framework.web.mvc.context.ServletApplicationContextInitializer;
import com.sunsharing.xshare.framework.web.mvc.filter.FilterDelegator;
import com.sunsharing.xshare.framework.web.mvc.filter.WelcomeHandlingFilter;

/**
 * 默认的Web应用程序配置类，Spring中Web应用程序的初始化顺序如下：
 * 
 * <pre class="code">
 * onStartup
 *     registerContextLoaderListener
 *         createRootApplicationContext
 *             getRootConfigClasses
 *         getRootApplicationContextInitializers
 *     registerDispatcherServlet
 *         getServletName
 *         createServletApplicationContext
 *             getServletConfigClasses
 *         createDispatcherServlet
 *         getServletApplicationContextInitializers
 *         getServletMappings
 *         isAsyncSupported
 *         getServletFilters
 *         registerServletFilter
 *             isAsyncSupported
 *             getDispatcherTypes
 *                 isAsyncSupported
 *             getServletName
 *         customizeRegistration
 * </pre>
 *
 * @author Kison 2017年3月21日
 */
public abstract class DefaultWebConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 默认的DispatcherServlet的servlet-mapping中的url-pattern
     */
    public static final String DEFAULT_SERVLET_MAPPING = "/*";

    /**
     * 在ServletContext中添加过滤器，并把过滤器应用于DispatcherServlet。<br>
     * 在ServletContext中添加监听器。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        registerServletFilters(servletContext);
        registerListeners(servletContext);
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected final void registerContextLoaderListener(ServletContext servletContext) {
        super.registerContextLoaderListener(servletContext);
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected final void registerDispatcherServlet(ServletContext servletContext) {
        super.registerDispatcherServlet(servletContext);
    }

    /**
     * 在ServletContext中添加过滤器，并把过滤器应用于DispatcherServlet。
     * 
     * @param servletContext Servlet上下文
     */
    protected final void registerServletFilters(ServletContext servletContext) {
        List<FilterDelegator> servletFilters = new ArrayList<>();
        configServletFilters(servletFilters);
        for (FilterDelegator filterDelegator : servletFilters) {
            if (StringUtils.hasText(filterDelegator.getFilterName())) {
                registerServletFilter(servletContext, filterDelegator.getFilter(), filterDelegator.getFilterName());
            } else {
                registerServletFilter(servletContext, filterDelegator.getFilter());
            }
        }
    }

    /**
     * 标记为final，禁止子类覆盖此方法，调用统一的getDispatcherTypes方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected final FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {
        String filterName = Conventions.getVariableName(filter);
        FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
        if (registration == null) {
            int counter = -1;
            while (counter == -1 || registration == null) {
                counter++;
                registration = servletContext.addFilter(filterName + "#" + counter, filter);
                Assert.isTrue(counter < 100, "Failed to register filter '" + filter + "'." + "Could the same Filter instance have been registered already?");
            }
        }
        registration.setAsyncSupported(isAsyncSupported());
        registration.addMappingForServletNames(getDispatcherTypes(), false, getServletName());
        return registration;
    }

    /**
     * 在ServletContext中添加过滤器，并把过滤器应用于DispatcherServlet。
     * 
     * @param servletContext Servlet上下文
     * @param filter 过滤器
     * @param filterName 过滤器名称
     */
    protected final void registerServletFilter(ServletContext servletContext, Filter filter, String filterName) {
        FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
        if (registration == null) {
            throw new IllegalStateException("Duplicate Filter registration for '" + filterName + "'. Check to ensure the Filter is only configured once.");
        }
        registration.setAsyncSupported(isAsyncSupported());
        registration.addMappingForServletNames(getDispatcherTypes(), false, getServletName());
    }

    /**
     * 在ServletContext中添加监听器。
     * 
     * @param servletContext Servlet上下文
     */
    protected final void registerListeners(ServletContext servletContext) {
        List<Class<? extends EventListener>> listenerClasses = new ArrayList<>();
        configListenerClasses(listenerClasses);
        for (Class<? extends EventListener> listenerClass : listenerClasses) {
            servletContext.addListener(listenerClass);
        }
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected final WebApplicationContext createRootApplicationContext() {
        return super.createRootApplicationContext();
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected final WebApplicationContext createServletApplicationContext() {
        return super.createServletApplicationContext();
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected final FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        return super.createDispatcherServlet(servletAppContext);
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected final Class<?>[] getRootConfigClasses() {
        List<Class<?>> rootConfigClasses = new ArrayList<>();
        configRootConfigClasses(rootConfigClasses);
        return rootConfigClasses.toArray(new Class<?>[rootConfigClasses.size()]);
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected final ApplicationContextInitializer<?>[] getRootApplicationContextInitializers() {
        List<ApplicationContextInitializer<?>> rootApplicationContextInitializers = new ArrayList<>();
        configRootApplicationContextInitializers(rootApplicationContextInitializers);
        return rootApplicationContextInitializers.toArray(new ApplicationContextInitializer<?>[rootApplicationContextInitializers.size()]);
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected final Class<?>[] getServletConfigClasses() {
        List<Class<?>> servletConfigClasses = new ArrayList<>();
        configServletConfigClasses(servletConfigClasses);
        return servletConfigClasses.toArray(new Class<?>[servletConfigClasses.size()]);
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected final ApplicationContextInitializer<?>[] getServletApplicationContextInitializers() {
        List<ApplicationContextInitializer<?>> servletApplicationContextInitializers = new ArrayList<>();
        configServletApplicationContextInitializers(servletApplicationContextInitializers);
        return servletApplicationContextInitializers.toArray(new ApplicationContextInitializer<?>[servletApplicationContextInitializers.size()]);
    }

    /**
     * 获取DispatcherServlet的Web应用上下文在ServletContext中的名称。
     * 
     * @return DispatcherServlet的Web应用上下文在ServletContext中的名称
     */
    protected final String getServletApplicationContextName() {
        return FrameworkServlet.SERVLET_CONTEXT_PREFIX + getServletName();
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected final String getServletName() {
        return super.getServletName();
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected final String[] getServletMappings() {
        List<String> servletMappings = new ArrayList<>();
        configServletMappings(servletMappings);
        return servletMappings.toArray(new String[servletMappings.size()]);
    }

    /**
     * 标记为final，禁止子类覆盖此方法。请使用configServletFilters方法来配置过滤器。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected final Filter[] getServletFilters() {
        return super.getServletFilters();
    }

    /**
     * 获取过滤器的分发器类型。<br>
     * 1、支持异步操作时，默认为：DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE、DispatcherType.ASYNC；<br>
     * 2、不支持异步操作时，默认为：DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE。
     * <p>
     * 
     * 参考：<br>
     * AbstractDispatcherServletInitializer.getDispatcherTypes()
     * 
     * @return DispatcherType集合
     */
    protected final EnumSet<DispatcherType> getDispatcherTypes() {
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE);
        if (isAsyncSupported()) {
            dispatcherTypes.add(DispatcherType.ASYNC);
        }
        configDispatcherTypes(dispatcherTypes);
        return dispatcherTypes;
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected final boolean isAsyncSupported() {
        return super.isAsyncSupported();
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    protected final void customizeRegistration(Dynamic registration) {
        // Do nothing
    }

    /**
     * 配置DispatcherServlet的过滤器。
     * 
     * @param servletFilters DispatcherServlet的过滤器
     */
    protected void configServletFilters(List<FilterDelegator> servletFilters) {
        servletFilters.add(new FilterDelegator(new CharacterEncodingFilter("UTF-8")));
        servletFilters.add(new FilterDelegator(new WelcomeHandlingFilter()));
        // servletFilters.add(new PlatformFilter());
    }

    /**
     * 配置监听器。
     * 
     * @param listenerClasses 监听器类
     */
    protected void configListenerClasses(List<Class<? extends EventListener>> listenerClasses) {

    }

    /**
     * 配置注册到根应用上下文的@Configuration配置类或@Component配置类。
     * 
     * @param rootConfigClasses 注册到根应用上下文的@Configuration配置类或@Component配置类
     */
    protected void configRootConfigClasses(List<Class<?>> rootConfigClasses) {
        // Do nothing
    }

    /**
     * 配置根应用上下文的初始化器。
     * 
     * @param rootApplicationContextInitializers 根应用上下文的初始化器
     */
    protected void configRootApplicationContextInitializers(List<ApplicationContextInitializer<?>> rootApplicationContextInitializers) {
        // Do nothing
    }

    /**
     * 配置注册到DispatcherServlet应用上下文的@Configuration配置类或@Component配置类。
     * 
     * @param servletConfigClasses 注册到DispatcherServlet应用上下文的@Configuration配置类或@Component配置类
     */
    protected void configServletConfigClasses(List<Class<?>> servletConfigClasses) {
        // Do nothing
    }

    /**
     * 配置DispatcherServlet应用上下文的初始化器。
     * 
     * @param servletApplicationContextInitializers DispatcherServlet应用上下文的初始化器
     */
    protected void configServletApplicationContextInitializers(List<ApplicationContextInitializer<?>> servletApplicationContextInitializers) {
        servletApplicationContextInitializers.add(new ServletApplicationContextInitializer());
    }

    /**
     * 配置DispatcherServlet的servlet-mapping中的url-pattern。
     * 
     * @param servletMappings DispatcherServlet的servlet-mapping中的url-pattern
     */
    protected void configServletMappings(List<String> servletMappings) {
        servletMappings.add(DEFAULT_SERVLET_MAPPING);
    }

    /**
     * 配置过滤器的分发器类型。
     * 
     * @param dispatcherTypes 过滤器的分发器类型
     */
    protected void configDispatcherTypes(EnumSet<DispatcherType> dispatcherTypes) {

    }

}
