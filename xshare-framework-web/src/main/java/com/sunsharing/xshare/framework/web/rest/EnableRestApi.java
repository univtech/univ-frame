/**
 * 
 */
package com.sunsharing.xshare.framework.web.rest;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.sunsharing.xshare.framework.web.rest.proxy.RestApiProxyConfiguration;

/**
 * 在Configuration注解的配置类上添加EnableRestApi注解，可以启用RestApi的动态代理。<br>
 * EnableRestApi注解中引入了RestApiProxyConfiguration配置类，这个配置类用于实现RestApi的动态代理。<br>
 * EnableRestApi注解的使用示例如下：<br>
 * 
 * <pre class="code">
 * &#064;Configuration
 * &#064;EnableRestApi
 * public class RestApiConfiguration {
 * 
 *     &#064;Bean
 *     public RestApiSettingRegistry restApiSettingRegistry() {
 *         RestApiSettingRegistry restApiSettingRegistry = new RestApiSettingRegistry();
 *         restApiSettingRegistry.register(userName, password, serverUrl, packageNames);
 *         return restApiSettingRegistry;
 *     }
 * 
 * }
 * </pre>
 *
 * @author Kison 2017年4月10日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RestApiProxyConfiguration.class)
public @interface EnableRestApi {

}
