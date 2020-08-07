/**
 * 
 */
package com.sunsharing.xshare.framework.db.ds.dbcp2;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sunsharing.xshare.framework.core.lang.StringUtils;
import com.sunsharing.xshare.framework.db.ds.DataSourceConfiguration;
import com.sunsharing.xshare.framework.db.ds.DataSourceSetting;

/**
 * commons-dbcp2数据源配置类。
 * 
 * @author Kison 2017年3月16日
 */
@Configuration
public class Dbcp2DataSourceConfiguration extends DataSourceConfiguration {

    @Override
    @Bean(destroyMethod = "close")
    public DataSource dataSource(DataSourceSetting dataSourceSetting) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dataSourceSetting.getDatabaseType().getDriverClassName());
        dataSource.setUrl(buildDataSourceUrl(dataSourceSetting));
        dataSource.setUsername(dataSourceSetting.getUserName());
        dataSource.setPassword(dataSourceSetting.getPassword());

        dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        dataSource.setValidationQuery(dataSourceSetting.getDatabaseType().getTestStatement());
        String connectionProperties = dataSourceSetting.getConnectionProperties();
        if (StringUtils.isNotEmpty(connectionProperties)) {
            dataSource.setConnectionProperties(connectionProperties);
        }
        // dbcpDataSource.setDatabaseName(env.getRequiredProperty("jdbc.databaseName")); TODO

        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(50);
        // dbcpDataSource.setMinIdle(1);
        dataSource.setMaxIdle(20);
        return dataSource;
    }

}
