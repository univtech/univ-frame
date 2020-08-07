/**
 * 
 */
package com.sunsharing.xshare.framework.db.ds.spring;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.sunsharing.xshare.framework.db.ds.DataSourceConfiguration;
import com.sunsharing.xshare.framework.db.ds.DataSourceSetting;

/**
 * spring-jdbc数据源配置类。
 * 
 * @author Kison 2017年3月16日
 */
@Configuration
public class SpringDataSourceConfiguration extends DataSourceConfiguration {

    @Override
    @Bean
    public DataSource dataSource(DataSourceSetting dataSourceSetting) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceSetting.getDatabaseType().getDriverClassName());
        dataSource.setUrl(buildDataSourceUrl(dataSourceSetting));
        dataSource.setUsername(dataSourceSetting.getUserName());
        dataSource.setPassword(dataSourceSetting.getPassword());
        dataSource.setConnectionProperties(convertConnectionProperties(dataSourceSetting));
        return dataSource;
    }

}
