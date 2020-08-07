/**
 * 
 */
package com.sunsharing.xshare.framework.web.security.cas;

import java.util.EventListener;
import java.util.List;

import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;

import com.sunsharing.xshare.framework.web.security.DefaultSecurityWebConfiguration;

/**
 * 默认的CAS Web应用程序配置类。
 *
 * @author Kison 2017年6月7日
 */
public abstract class DefaultCasWebConfiguration extends DefaultSecurityWebConfiguration {

    @Override
    protected void configListenerClasses(List<Class<? extends EventListener>> listenerClasses) {
        super.configListenerClasses(listenerClasses);
        listenerClasses.add(SingleSignOutHttpSessionListener.class);
    }

}
