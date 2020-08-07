/**
 * 
 */
package com.sunsharing.xshare.framework.db.ds;

/**
 * 数据源配置器，用于配置数据源属性。
 *
 * @author Kison 2017年3月18日
 */
public class DataSourceSetting {

    /** 数据库类型 */
    private DatabaseType databaseType;

    /** 数据库主机 */
    private String host;

    /** 数据库端口 */
    private String port;

    /** 数据库名 */
    private String databaseName;

    /** 数据库用户名 */
    private String userName;

    /** 数据库密码 */
    private String password;

    /** 数据库连接参数，格式为：[propertyName=property;]* */
    private String connectionProperties;

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(String connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

}
