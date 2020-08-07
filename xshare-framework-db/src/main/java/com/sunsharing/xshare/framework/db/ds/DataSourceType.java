/**
 * 
 */
package com.sunsharing.xshare.framework.db.ds;

import com.sunsharing.xshare.framework.db.ds.c3p0.C3p0DataSourceConfiguration;
import com.sunsharing.xshare.framework.db.ds.dbcp2.Dbcp2DataSourceConfiguration;
import com.sunsharing.xshare.framework.db.ds.druid.DruidDataSourceConfiguration;
import com.sunsharing.xshare.framework.db.ds.hikaricp.HikaricpDataSourceConfiguration;
import com.sunsharing.xshare.framework.db.ds.spring.SpringDataSourceConfiguration;
import com.sunsharing.xshare.framework.db.ds.tomcat.TomcatDataSourceConfiguration;

/**
 * 数据源类型。
 *
 * @author Kison 2017年5月10日
 */
public enum DataSourceType {

    /**
     * spring-jdbc数据源
     */
    SPRING(SpringDataSourceConfiguration.class.getName()),

    /**
     * commons-dbcp2数据源
     */
    DBCP2(Dbcp2DataSourceConfiguration.class.getName()),

    /**
     * c3p0数据源
     */
    C3P0(C3p0DataSourceConfiguration.class.getName()),

    /**
     * tomcat-jdbc数据源
     */
    TOMCAT(TomcatDataSourceConfiguration.class.getName()),

    /**
     * HikariCP数据源
     */
    HIKARICP(HikaricpDataSourceConfiguration.class.getName()),

    /**
     * druid数据源
     */
    DRUID(DruidDataSourceConfiguration.class.getName());

    /**
     * 数据源配置类
     */
    private String dataSourceConfigurationClassName;

    /**
     * 创建数据源类型。
     * 
     * @param dataSourceConfigurationClassName 数据源配置类
     */
    private DataSourceType(String dataSourceConfigurationClassName) {
        this.dataSourceConfigurationClassName = dataSourceConfigurationClassName;
    }

    /**
     * 获取数据源配置类。
     * 
     * @return 数据源配置类
     */
    public String getDataSourceConfigurationClassName() {
        return dataSourceConfigurationClassName;
    }

}
