/**
 * 
 */
package com.sunsharing.xshare.framework.db.ds.tomcat;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sunsharing.xshare.framework.db.ds.DataSourceConfiguration;
import com.sunsharing.xshare.framework.db.ds.DataSourceSetting;

/**
 * tomcat-jdbc数据源配置类。
 * 
 * @author Kison 2017年3月16日
 */
@Configuration
public class TomcatDataSourceConfiguration extends DataSourceConfiguration {

    @Override
    @Bean
    public DataSource dataSource(DataSourceSetting dataSourceSetting) {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        // dataSource.setDriverClassName(driverClassName);
        // dataSource.setUrl(url);
        // dataSource.setUsername(username);
        // dataSource.setPassword(password);
        return dataSource;
    }

}
