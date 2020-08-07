/**
 * 
 */
package com.sunsharing.xshare.framework.db.dao.hibernate;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * Hibernate数据访问对象。
 *
 * @author Kison 2017年3月20日
 */
@Repository
public class HibernateDao {

    @Inject
    private SessionFactory sessionFactory;

    public Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

}
