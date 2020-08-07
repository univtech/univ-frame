/**
 * 
 */
package com.sunsharing.xshare.framework.web.security.cas;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.Filter;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.sunsharing.xshare.framework.web.security.DefaultWebSecurityConfiguration;
import com.sunsharing.xshare.framework.web.security.SecuritySetting;

/**
 * 默认的CAS配置类。
 *
 * @author Kison 2017年5月15日
 */
public abstract class DefaultCasWebSecurityConfiguration extends DefaultWebSecurityConfiguration {

    @Inject
    private SecuritySetting securitySetting;
    
    @Inject
    private CasSetting casSetting;

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
        casAuthenticationFilter.setFilterProcessesUrl(CasSetting.DEFAULT_LOGIN_PROCESS_URL);
        casAuthenticationFilter.setAuthenticationManager(authenticationManager());
        casAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        return casAuthenticationFilter;
    }

    private SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        authenticationSuccessHandler.setDefaultTargetUrl(securitySetting.getLoginSuccessUrl());
        return authenticationSuccessHandler;
    }

    @Bean
    public LogoutFilter casLogoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter(casSetting.getCasLogoutUrl(), new SecurityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl(CasSetting.DEFAULT_LOGOUT_PROCESS_URL);
        return logoutFilter;
    }

    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        return new SingleSignOutFilter();
    }

    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
        CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
        casAuthenticationEntryPoint.setLoginUrl(casSetting.getCasLoginUrl());
        casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
        return casAuthenticationEntryPoint;
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        casAuthenticationProvider.setKey("casAuthenticationProvider");
        casAuthenticationProvider.setServiceProperties(serviceProperties());
        casAuthenticationProvider.setUserDetailsService(userDetailsService());
        casAuthenticationProvider.setTicketValidator(new Cas20ServiceTicketValidator(casSetting.getCasServerUrl()));
        return casAuthenticationProvider;
    }

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(casSetting.getServiceUrl());
        serviceProperties.setSendRenew(false);
        return serviceProperties;
    }

    @Override
    protected void configFilters(List<Filter> filters) throws Exception {
        filters.add(casAuthenticationFilter());
    }

    @Override
    protected void configFiltersBefore(Map<Filter, Class<? extends Filter>> filterBeforeMap) throws Exception {
        filterBeforeMap.put(casLogoutFilter(), LogoutFilter.class);
        filterBeforeMap.put(singleSignOutFilter(), CasAuthenticationFilter.class);
    }

    @Override
    protected void configExceptionHandling(ExceptionHandlingConfigurer<HttpSecurity> exceptionHandlingConfigurer) {
        exceptionHandlingConfigurer.authenticationEntryPoint(casAuthenticationEntryPoint());
    }

    @Override
    protected void configAuthenticationProviders(List<AuthenticationProvider> authenticationProviders) {
        authenticationProviders.add(casAuthenticationProvider());
    }

}
