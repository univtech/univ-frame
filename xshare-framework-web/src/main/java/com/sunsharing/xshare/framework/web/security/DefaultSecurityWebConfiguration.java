/**
 * 
 */
package com.sunsharing.xshare.framework.web.security;

import java.util.EnumSet;
import java.util.EventListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.sunsharing.xshare.framework.web.mvc.DefaultWebConfiguration;
import com.sunsharing.xshare.framework.web.mvc.filter.FilterDelegator;

/**
 * 默认的Security Web应用程序配置类。<br>
 * 整合了Spring Security中的AbstractSecurityWebApplicationInitializer。
 *
 * @author Kison 2017年5月16日
 */
public abstract class DefaultSecurityWebConfiguration extends DefaultWebConfiguration {

    /**
     * 在ServletContext中设置会话跟踪模式。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        registerSessionTrackingModes(servletContext);
    }

    /**
     * 在ServletContext中设置会话跟踪模式。
     * 
     * @param servletContext Servlet上下文
     */
    protected final void registerSessionTrackingModes(ServletContext servletContext) {
        Set<SessionTrackingMode> sessionTrackingModes = new HashSet<>();
        configSessionTrackingModes(sessionTrackingModes);
        servletContext.setSessionTrackingModes(sessionTrackingModes);
    }

    /**
     * 创建springSecurityFilterChain过滤器。
     * 
     * @return springSecurityFilterChain过滤器
     */
    protected final Filter createSpringSecurityFilterChain() {
        DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy(getSpringSecurityFilterChainName());
        springSecurityFilterChain.setContextAttribute(getServletApplicationContextName());
        return springSecurityFilterChain;
    }

    /**
     * 获取springSecurityFilterChain过滤器的名称。
     * 
     * @return springSecurityFilterChain过滤器的名称
     */
    protected final String getSpringSecurityFilterChainName() {
        return AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME;
    }

    /**
     * 配置springSecurityFilterChain过滤器。
     */
    @Override
    protected void configServletFilters(List<FilterDelegator> servletFilters) {
        super.configServletFilters(servletFilters);
        servletFilters.add(new FilterDelegator(createSpringSecurityFilterChain(), getSpringSecurityFilterChainName()));
    }

    /**
     * 配置HttpSessionEventPublisher监听器。
     */
    @Override
    protected void configListenerClasses(List<Class<? extends EventListener>> listenerClasses) {
        listenerClasses.add(HttpSessionEventPublisher.class);
    }

    /**
     * 配置DispatcherType.ERROR分发器类型。
     */
    @Override
    protected void configDispatcherTypes(EnumSet<DispatcherType> dispatcherTypes) {
        dispatcherTypes.add(DispatcherType.ERROR);
    }

    /**
     * 配置会话跟踪模式，默认为SessionTrackingMode.COOKIE。
     * 
     * @param sessionTrackingModes 会话跟踪模式
     */
    protected void configSessionTrackingModes(Set<SessionTrackingMode> sessionTrackingModes) {
        sessionTrackingModes.add(SessionTrackingMode.COOKIE);
    }

}
