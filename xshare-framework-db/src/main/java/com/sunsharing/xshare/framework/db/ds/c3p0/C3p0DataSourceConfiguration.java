/**
 * 
 */
package com.sunsharing.xshare.framework.db.ds.c3p0;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sunsharing.xshare.framework.core.exception.UncheckedException;
import com.sunsharing.xshare.framework.db.ds.DataSourceConfiguration;
import com.sunsharing.xshare.framework.db.ds.DataSourceSetting;

/**
 * c3p0数据源配置类。
 * 
 * @author Kison 2017年3月16日
 */
@Configuration
public class C3p0DataSourceConfiguration extends DataSourceConfiguration {

    @Override
    @Bean(destroyMethod = "close")
    public DataSource dataSource(DataSourceSetting dataSourceSetting) {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(dataSourceSetting.getDatabaseType().getDriverClassName());
        } catch (PropertyVetoException e) {
            throw new UncheckedException(e.getMessage(), e);
        }
        dataSource.setJdbcUrl(buildDataSourceUrl(dataSourceSetting));
        dataSource.setUser(dataSourceSetting.getUserName());
        dataSource.setPassword(dataSourceSetting.getPassword());

        // dataSource.setConnectionProperties(connectionProperties); TODO
        dataSource.setPreferredTestQuery(dataSourceSetting.getDatabaseType().getTestStatement());
        // dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        // dataSource.setDatabaseName(env.getRequiredProperty("jdbc.databaseName")); TODO

        dataSource.setMinPoolSize(5);
        dataSource.setAcquireIncrement(5);
        dataSource.setMaxPoolSize(20);

        return dataSource;
    }

}
