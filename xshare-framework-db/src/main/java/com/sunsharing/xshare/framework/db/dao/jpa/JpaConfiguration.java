/**
 * 
 */
package com.sunsharing.xshare.framework.db.dao.jpa;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * JPA配置类。
 * 
 * @author Kison 2017年3月15日
 */
@Configuration
public class JpaConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaSetting jpaSetting) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan(jpaSetting.getBasePackages());
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter(jpaSetting));
        return entityManagerFactory;
    }

    private JpaVendorAdapter jpaVendorAdapter(JpaSetting jpaSetting) {
        // HibernateJpaVendorAdapter
        // OpenJpaVendorAdapter
        // EclipseLinkJpaVendorAdapter
        AbstractJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(jpaSetting.getDatabase());
        jpaVendorAdapter.setShowSql(jpaSetting.isShowSql());
        jpaVendorAdapter.setGenerateDdl(jpaSetting.isGenerateDdl());
        return jpaVendorAdapter;
    }

    @Bean
    public JpaTransactionManager jpaTransactionManager(DataSource dataSource) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public JpaDao jpaDao() {
        return new JpaDao();
    }

}
