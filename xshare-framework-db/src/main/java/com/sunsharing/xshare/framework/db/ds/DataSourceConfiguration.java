/**
 * 
 */
package com.sunsharing.xshare.framework.db.ds;

import java.text.MessageFormat;
import java.util.Properties;

import javax.sql.DataSource;

import com.sunsharing.xshare.framework.core.lang.StringUtils;

/**
 * 数据源配置类，用于创建DataSource Bean。
 * 
 * @author Kison 2017年3月16日
 */
public abstract class DataSourceConfiguration {

    /**
     * 创建DataSource Bean。
     *
     * @param dataSourceSetting 数据源配置器
     * @return DataSource Bean
     */
    public abstract DataSource dataSource(DataSourceSetting dataSourceSetting);

    /**
     * 构造数据源URL。
     * 
     * @param dataSourceSetting 数据源配置器
     * @return 数据源URL
     */
    protected String buildDataSourceUrl(DataSourceSetting dataSourceSetting) {
        String urlPattern = dataSourceSetting.getDatabaseType().getUrlPattern();
        String host = dataSourceSetting.getHost();
        String port = dataSourceSetting.getPort();
        String databaseName = dataSourceSetting.getDatabaseName();
        return MessageFormat.format(urlPattern, host, port, databaseName);
    }

    /**
     * 把[propertyName=property;]*格式的数据库连接参数转换为Properties对象。
     * 
     * @param dataSourceSetting 数据源配置器
     * @return Properties对象
     */
    protected Properties convertConnectionProperties(DataSourceSetting dataSourceSetting) {
        Properties properties = new Properties();
        String connectionProperties = dataSourceSetting.getConnectionProperties();
        if (StringUtils.hasText(connectionProperties)) {
            String[] props = connectionProperties.split(";");
            for (String prop : props) {
                String[] entry = prop.split("=");
                properties.put(entry[0], entry[1]);
            }
        }
        return properties;
    }

}
