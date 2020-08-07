/**
 * 
 */
package com.sunsharing.xshare.framework.db;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.sunsharing.xshare.framework.db.dao.DaoConfigurationSelector;
import com.sunsharing.xshare.framework.db.dao.DaoType;
import com.sunsharing.xshare.framework.db.ds.DataSourceConfigurationSelector;
import com.sunsharing.xshare.framework.db.ds.DataSourceType;

/**
 * 启用数据库访问。
 * <p>
 * 
 * 通过dataSourceType属性，可以选择以下数据源：<br>
 * 1、spring-jdbc数据源；<br>
 * 2、commons-dbcp2数据源；<br>
 * 3、c3p0数据源；<br>
 * 4、tomcat-jdbc数据源；<br>
 * 5、HikariCP数据源；<br>
 * 6、druid数据源。<br>
 * <p>
 * 
 * 通过daoType属性，可以选择以下DAO：<br>
 * 1、Jdbc DAO；<br>
 * 2、MyBatis DAO；<br>
 * 3、Hibernate DAO；<br>
 * 4、Jpa DAO。<br>
 * <p>
 * 
 * 使用示例如下：
 * <pre class="code">
 * &#064;Configuration
 * &#064;EnableTransactionManagement
 * &#064;EnableDatabaseAccess(dataSourceType = DataSourceType.DBCP2, daoType = { DaoType.JPA })
 * public class DatabaseConfiguration {
 * 
 *     &#064;Bean
 *     public DataSourceSetting dataSourceSetting() {
 *         DataSourceSetting dataSourceSetting = new DataSourceSetting();
 *         dataSourceSetting.setDatabaseType(databaseType);
 *         dataSourceSetting.setHost(host);
 *         dataSourceSetting.setPort(port);
 *         dataSourceSetting.setDatabaseName(databaseName);
 *         dataSourceSetting.setUserName(userName);
 *         dataSourceSetting.setPassword(password);
 *         return dataSourceSetting;
 *     }
 * 
 *     &#064;Bean
 *     public JpaSetting jpaSetting() {
 *         JpaSetting jpaSetting = new JpaSetting();
 *         jpaSetting.addBasePackage(basePackage);
 *         jpaSetting.setDatabase(database);
 *         jpaSetting.setShowSql(showSql);
 *         return jpaSetting;
 *     }
 * 
 * }
 * </pre>
 * 
 * @author Kison 2017年5月10日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(value = { DataSourceConfigurationSelector.class, DaoConfigurationSelector.class })
public @interface EnableDatabaseAccess {

    /**
     * 数据源类型
     */
    DataSourceType dataSourceType() default DataSourceType.SPRING;

    /**
     * DAO类型
     */
    DaoType[] daoType();

}
