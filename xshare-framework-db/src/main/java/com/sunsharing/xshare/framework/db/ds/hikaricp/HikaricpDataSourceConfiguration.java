/**
 * 
 */
package com.sunsharing.xshare.framework.db.ds.hikaricp;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sunsharing.xshare.framework.db.ds.DataSourceConfiguration;
import com.sunsharing.xshare.framework.db.ds.DataSourceSetting;

/**
 * HikariCP数据源配置类。
 * 
 * @author Kison 2017年3月16日
 */
@Configuration
public class HikaricpDataSourceConfiguration extends DataSourceConfiguration {

    @Override
    @Bean
    public DataSource dataSource(DataSourceSetting dataSourceSetting) {
        return null;
    }

}
