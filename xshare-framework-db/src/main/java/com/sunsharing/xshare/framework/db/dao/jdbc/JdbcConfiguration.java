/**
 * 
 */
package com.sunsharing.xshare.framework.db.dao.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JDBC配置类。
 *
 * @author Kison 2017年3月16日
 */
@Configuration
public class JdbcConfiguration {

    @Bean
    public JdbcDao jdbcDao() {
        return new JdbcDao();
    }

}
