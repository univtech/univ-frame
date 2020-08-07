/**
 * 
 */
package com.sunsharing.xshare.framework.db.ds;

/**
 * 数据库类型。
 * 
 * @author Kison 2017年3月21日
 */
public enum DatabaseType {

    /**
     * Oracle数据库
     */
    ORACLE("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@{0}:{1}:{2}", "select 1 from dual"),

    /**
     * MySQL数据库
     */
    MYSQL("com.mysql.jdbc.Driver", "jdbc:mysql://{0}:{1}/{2}", "select 1");

    /**
     * 驱动程序类名
     */
    private String driverClassName;

    /**
     * URL模式
     */
    private String urlPattern;

    /**
     * 测试语句
     */
    private String testStatement;

    /**
     * 创建数据库类型。
     * 
     * @param driverClassName 驱动程序类名
     * @param urlPattern URL模式
     * @param testStatement 测试语句
     */
    private DatabaseType(String driverClassName, String urlPattern, String testStatement) {
        this.driverClassName = driverClassName;
        this.urlPattern = urlPattern;
        this.testStatement = testStatement;
    }

    /**
     * 获取驱动程序类名。
     * 
     * @return 驱动程序类名
     */
    public String getDriverClassName() {
        return driverClassName;
    }

    /**
     * 获取URL模式。
     * 
     * @return URL模式
     */
    public String getUrlPattern() {
        return urlPattern;
    }

    /**
     * 获取测试语句。
     * 
     * @return 测试语句
     */
    public String getTestStatement() {
        return testStatement;
    }

}
