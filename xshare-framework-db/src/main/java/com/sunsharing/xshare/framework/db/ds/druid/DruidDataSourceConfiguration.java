/**
 * 
 */
package com.sunsharing.xshare.framework.db.ds.druid;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sunsharing.xshare.framework.db.ds.DataSourceConfiguration;
import com.sunsharing.xshare.framework.db.ds.DataSourceSetting;

/**
 * druid数据源配置类。
 * 
 * @author Kison 2017年3月16日
 */
@Configuration
public class DruidDataSourceConfiguration extends DataSourceConfiguration {

    @Override
    @Bean
    public DataSource dataSource(DataSourceSetting dataSourceSetting) {
        return null;
    }

}
