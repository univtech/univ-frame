/**
 * 
 */
package com.sunsharing.xshare.framework.web.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AnonymousConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.accept.ContentNegotiationStrategy;

/**
 * 默认的WebSecurity配置类。
 *
 * @author Kison 2017年5月15日
 */
public abstract class DefaultWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Inject
    private SecuritySetting securitySetting;

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    public final void init(WebSecurity webSecurity) throws Exception {
        super.init(webSecurity);
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    public final void configure(WebSecurity webSecurity) throws Exception {
        super.configure(webSecurity);
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    public final void configure(HttpSecurity httpSecurity) throws Exception {
        configFilters(httpSecurity);
        configAuthenticationProviders(httpSecurity);
        configHttpBasic(httpSecurity.httpBasic());
        configAuthorizeRequests(httpSecurity.authorizeRequests());
        configHeaders(httpSecurity.headers());
        configFormLogin(httpSecurity.formLogin());
        configLogout(httpSecurity.logout());
        configAnonymous(httpSecurity.anonymous());
        configRememberMe(httpSecurity.rememberMe());
        configExceptionHandling(httpSecurity.exceptionHandling());
        configSessionManagement(httpSecurity.sessionManagement());
        configCsrf(httpSecurity.csrf());
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    public final void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        super.configure(authenticationManagerBuilder);
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    @Autowired
    public final void setObjectPostProcessor(ObjectPostProcessor<Object> objectPostProcessor) {
        super.setObjectPostProcessor(objectPostProcessor);
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    @Autowired
    public final void setApplicationContext(ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    @Autowired
    public final void setAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration) {
        super.setAuthenticationConfiguration(authenticationConfiguration);
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    @Autowired(required = false)
    public final void setContentNegotationStrategy(ContentNegotiationStrategy contentNegotiationStrategy) {
        super.setContentNegotationStrategy(contentNegotiationStrategy);
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    @Autowired(required = false)
    public final void setTrustResolver(AuthenticationTrustResolver authenticationTrustResolver) {
        super.setTrustResolver(authenticationTrustResolver);
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    public final AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    public final AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * 标记为final，禁止子类覆盖此方法。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    public final UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    /**
     * 子类应该覆盖并使用@Bean注解来标记此方法，返回自定义的UserDetailsService对象。
     * <p>
     * 
     * {@inheritDoc}
     */
    @Override
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    /**
     * 配置过滤器。
     * 
     * @param httpSecurity HttpSecurity对象
     * @throws Exception 异常
     */
    public final void configFilters(HttpSecurity httpSecurity) throws Exception {
        List<Filter> filters = new ArrayList<>();
        configFilters(filters);
        for (Filter filter : filters) {
            httpSecurity.addFilter(filter);
        }

        Map<Filter, Class<? extends Filter>> filterBeforeMap = new HashMap<>();
        configFiltersBefore(filterBeforeMap);
        for (Entry<Filter, Class<? extends Filter>> filterBeforeEntry : filterBeforeMap.entrySet()) {
            httpSecurity.addFilterBefore(filterBeforeEntry.getKey(), filterBeforeEntry.getValue());
        }
    }

    /**
     * 配置AuthenticationProvider对象。
     * 
     * @param httpSecurity HttpSecurity对象
     */
    public final void configAuthenticationProviders(HttpSecurity httpSecurity) {
        List<AuthenticationProvider> authenticationProviders = new ArrayList<>();
        configAuthenticationProviders(authenticationProviders);
        for (AuthenticationProvider authenticationProvider : authenticationProviders) {
            httpSecurity.authenticationProvider(authenticationProvider);
        }
    }

    protected void configFilters(List<Filter> filters) throws Exception {

    }

    protected void configFiltersBefore(Map<Filter, Class<? extends Filter>> filterBeforeMap) throws Exception {

    }

    protected void configAuthenticationProviders(List<AuthenticationProvider> authenticationProviders) {

    }

    protected void configHttpBasic(HttpBasicConfigurer<HttpSecurity> httpBasicConfigurer) {

    }

    protected void configAuthorizeRequests(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry) {

    }

    protected void configHeaders(HeadersConfigurer<HttpSecurity> headersConfigurer) {

    }

    protected void configFormLogin(FormLoginConfigurer<HttpSecurity> formLoginConfigurer) {
        formLoginConfigurer.usernameParameter(securitySetting.getUsernameParameter());
        formLoginConfigurer.passwordParameter(securitySetting.getPasswordParameter());

        formLoginConfigurer.loginPage(securitySetting.getLoginUrl());
        formLoginConfigurer.loginProcessingUrl(securitySetting.getLoginProcessUrl()); // 这个请求路径不需要在控制器中实现，必须以POST方法提交这个请求
        formLoginConfigurer.defaultSuccessUrl(securitySetting.getLoginSuccessUrl());
        formLoginConfigurer.failureUrl(securitySetting.getLoginFailureUrl());
        formLoginConfigurer.permitAll();
    }

    protected void configLogout(LogoutConfigurer<HttpSecurity> logoutConfigurer) {
        logoutConfigurer.clearAuthentication(true);
        logoutConfigurer.invalidateHttpSession(true);
        logoutConfigurer.deleteCookies("JSESSIONID");

        logoutConfigurer.logoutUrl(securitySetting.getLogoutUrl()); // 这个请求路径不需要在控制器中实现，必须以POST方法提交这个请求
        logoutConfigurer.logoutSuccessUrl(securitySetting.getLogoutSuccessUrl());
        logoutConfigurer.permitAll();
    }

    protected void configAnonymous(AnonymousConfigurer<HttpSecurity> anonymousConfigurer) {

    }

    protected void configRememberMe(RememberMeConfigurer<HttpSecurity> rememberMeConfigurer) {

    }

    protected void configExceptionHandling(ExceptionHandlingConfigurer<HttpSecurity> exceptionHandlingConfigurer) {

    }

    protected void configSessionManagement(SessionManagementConfigurer<HttpSecurity> sessionManagementConfigurer) {

    }

    protected void configCsrf(CsrfConfigurer<HttpSecurity> csrfConfigurer) {

    }

}
