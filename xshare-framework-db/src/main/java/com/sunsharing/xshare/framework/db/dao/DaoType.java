/**
 * 
 */
package com.sunsharing.xshare.framework.db.dao;

import com.sunsharing.xshare.framework.db.dao.hibernate.HibernateConfiguration;
import com.sunsharing.xshare.framework.db.dao.jdbc.JdbcConfiguration;
import com.sunsharing.xshare.framework.db.dao.jpa.JpaConfiguration;
import com.sunsharing.xshare.framework.db.dao.mybatis.MyBatisConfiguration;

/**
 * DAO类型。
 *
 * @author Kison 2017年5月10日
 */
public enum DaoType {

    /**
     * Jdbc DAO
     */
    JDBC(JdbcConfiguration.class.getName()),

    /**
     * MyBatis DAO
     */
    MYBATIS(MyBatisConfiguration.class.getName()),

    /**
     * Hibernate DAO
     */
    HIBERNATE(HibernateConfiguration.class.getName()),

    /**
     * Jpa DAO
     */
    JPA(JpaConfiguration.class.getName());

    /**
     * DAO配置类
     */
    private String daoConfigurationClassName;

    /**
     * 创建DAO类型。
     * 
     * @param daoConfigurationClassName DAO配置类
     */
    private DaoType(String daoConfigurationClassName) {
        this.daoConfigurationClassName = daoConfigurationClassName;
    }

    /**
     * 获取DAO配置类。
     * 
     * @return DAO配置类
     */
    public String getDaoConfigurationClassName() {
        return daoConfigurationClassName;
    }

}
