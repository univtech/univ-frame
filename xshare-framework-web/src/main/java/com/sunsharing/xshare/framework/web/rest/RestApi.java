/**
 * 
 */
package com.sunsharing.xshare.framework.web.rest;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RestApi注解用于标注应用程序接口。
 *
 * @author Kison 2017年4月10日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface RestApi {

}
