/**
 * 
 */
package com.sunsharing.xshare.framework.db.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.jpa.vendor.Database;

/**
 * JPA配置器，用于配置JPA属性。
 * 
 * @author Kison 2017年3月16日
 */
public class JpaSetting {

    /** 实体所属的包名 */
    private final List<String> basePackages = new ArrayList<>();

    /** Database类型 */
    private Database database;

    /** 是否在日志或控制台输出SQL语句 */
    private boolean showSql = false;

    /** 是否生成用于创建或更新数据库表的DDL语句 */
    private boolean generateDdl = false;

    public String[] getBasePackages() {
        return basePackages.toArray(new String[basePackages.size()]);
    }

    public void addBasePackage(String basePackage) {
        basePackages.add(basePackage);
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public boolean isShowSql() {
        return showSql;
    }

    public void setShowSql(boolean showSql) {
        this.showSql = showSql;
    }

    public boolean isGenerateDdl() {
        return generateDdl;
    }

    public void setGenerateDdl(boolean generateDdl) {
        this.generateDdl = generateDdl;
    }

}
