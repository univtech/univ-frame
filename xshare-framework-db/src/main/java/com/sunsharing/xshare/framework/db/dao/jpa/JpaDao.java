/**
 * 
 */
package com.sunsharing.xshare.framework.db.dao.jpa;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.sunsharing.xshare.framework.core.assertion.ParameterAssertor;
import com.sunsharing.xshare.framework.core.collection.MapUtils;

/**
 * JPA数据访问对象。
 *
 * @author Kison 2017年3月20日
 */
public class JpaDao {

    /** 实体管理器 */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 管理并持久化指定实体。
     * 
     * @param entity 实体对象
     */
    public void save(Object entity) {
        ParameterAssertor.isNotNull(entity, "实体对象entity不能为空。");
        entityManager.persist(entity);
    }

    /**
     * 把指定实体的状态合并到当前持久化上下文中。
     * 
     * @param entity 实体对象
     * @return 托管状态的实体对象
     */
    public <T> T update(T entity) {
        ParameterAssertor.isNotNull(entity, "实体对象entity不能为空。");
        return entityManager.merge(entity);
    }

    /**
     * 更新指定类型的实体。
     * 
     * @param entityClass 实体类
     * @param updateFields 更新字段
     * @param updateConditions 更新条件
     * @return 更新的实体数
     */
    public int update(Class<?> entityClass, Map<String, Object> updateFields, Map<String, Object> updateConditions) {
        ParameterAssertor.isNotNull(entityClass, "实体类entityClass不能为空。");
        ParameterAssertor.isNotEmpty(updateFields, "更新字段updateFields不能为空。");
        String updateString = buildUpdateStatement(entityClass, updateFields, updateConditions);
        return update(updateString, updateFields, updateConditions);
    }

    /**
     * 更新实体。
     * 
     * @param updateString 更新语句
     * @param updateFields 更新字段
     * @param updateConditions 更新条件
     * @return 更新的实体数
     */
    public int update(String updateString, Map<String, Object> updateFields, Map<String, Object> updateConditions) {
        ParameterAssertor.hasText(updateString, "更新语句updateString不能为空。");
        ParameterAssertor.isNotEmpty(updateFields, "更新字段updateFields不能为空。");
        Query query = entityManager.createQuery(updateString);
        setParameters(query, updateFields);
        setParameters(query, updateConditions);
        return query.executeUpdate();
    }

    /**
     * 删除指定实体。
     * 
     * @param entity 实体对象
     */
    public void delete(Object entity) {
        ParameterAssertor.isNotNull(entity, "实体对象entity不能为空。");
        entityManager.remove(entity);
    }

    /**
     * 删除指定类型的实体。
     * 
     * @param entityClass 实体类
     * @param deleteConditions 删除条件
     * @return 删除的实体数
     */
    public int delete(Class<?> entityClass, Map<String, Object> deleteConditions) {
        ParameterAssertor.isNotNull(entityClass, "实体类entityClass不能为空。");
        String deleteString = buildDeleteStatement(entityClass, deleteConditions);
        return delete(deleteString, deleteConditions);
    }

    /**
     * 删除实体。
     * 
     * @param deleteString 删除语句
     * @param deleteConditions 删除条件
     * @return 删除的实体数
     */
    public int delete(String deleteString, Map<String, Object> deleteConditions) {
        ParameterAssertor.hasText(deleteString, "删除语句deleteString不能为空。");
        Query query = entityManager.createQuery(deleteString);
        setParameters(query, deleteConditions);
        return query.executeUpdate();
    }

    /**
     * 从持久化上下文中删除指定实体，使实体由托管状态变为游离状态。<br>
     * 对实体的任何修改（包括删除实体），如果没有被flush的话，都不会被同步到数据库中。<br>
     * 之前引用这个游离态实体的实体对象会继续引用这个游离态实体。
     * 
     * @param entity 实体对象
     */
    public void detach(Object entity) {
        ParameterAssertor.isNotNull(entity, "实体对象entity不能为空。");
        entityManager.detach(entity);
    }

    /**
     * 从数据库中刷新指定实体的状态，覆盖掉对实体的任何修改。
     * 
     * @param entity 实体对象
     */
    public void refresh(Object entity) {
        ParameterAssertor.isNotNull(entity, "实体对象entity不能为空。");
        entityManager.refresh(entity);
    }

    /**
     * 查询指定类型的实体总数。
     * 
     * @param entityClass 实体类
     * @param queryConditions 查询条件
     * @return 实体总数
     */
    public <T> Long count(Class<T> entityClass, Map<String, Object> queryConditions) {
        ParameterAssertor.isNotNull(entityClass, "实体类entityClass不能为空。");
        String queryString = buildCountStatement(entityClass, queryConditions);
        return count(queryString, queryConditions);
    }

    /**
     * 查询实体总数。
     * 
     * @param queryString 查询语句
     * @param queryConditions 查询条件
     * @return 实体总数
     */
    public Long count(String queryString, Map<String, Object> queryConditions) {
        ParameterAssertor.hasText(queryString, "查询语句queryString不能为空。");
        TypedQuery<Long> query = entityManager.createQuery(queryString, Long.class);
        setParameters(query, queryConditions);
        return query.getSingleResult();
    }

    /**
     * 查询指定类型的实体。
     * 
     * @param entityClass 实体类
     * @param id ID
     * @return 实体对象
     */
    public <T> T get(Class<T> entityClass, Object id) {
        ParameterAssertor.isNotNull(entityClass, "实体类entityClass不能为空。");
        ParameterAssertor.isNotNull(id, "ID不能为空。");
        return entityManager.find(entityClass, id);
    }

    /**
     * 查询指定类型的实体。
     * 
     * @param entityClass 实体类
     * @param queryConditions 查询条件
     * @return 实体对象
     */
    public <T> T get(Class<T> entityClass, Map<String, Object> queryConditions) {
        ParameterAssertor.isNotNull(entityClass, "实体类entityClass不能为空。");
        String queryString = buildSelectStatement(entityClass, queryConditions);
        return get(queryString, entityClass, queryConditions);
    }

    /**
     * 查询指定类型的实体。
     * 
     * @param queryString 查询语句
     * @param entityClass 实体类
     * @param queryConditions 查询条件
     * @return 实体对象
     */
    public <T> T get(String queryString, Class<T> entityClass, Map<String, Object> queryConditions) {
        ParameterAssertor.hasText(queryString, "查询语句queryString不能为空。");
        ParameterAssertor.isNotNull(entityClass, "实体类entityClass不能为空。");
        TypedQuery<T> query = entityManager.createQuery(queryString, entityClass);
        setParameters(query, queryConditions);
        return query.getSingleResult();
    }

    /**
     * 查询指定类型的实体。
     * 
     * @param entityClass 实体类
     * @param queryConditions 查询条件
     * @return 实体对象列表
     */
    public <T> List<T> query(Class<T> entityClass, Map<String, Object> queryConditions) {
        ParameterAssertor.isNotNull(entityClass, "实体类entityClass不能为空。");
        String queryString = buildSelectStatement(entityClass, queryConditions);
        return query(queryString, entityClass, queryConditions);
    }

    /**
     * 分页查询指定类型的实体。
     * 
     * @param entityClass 实体类
     * @param queryConditions 查询条件
     * @param start 起始位置
     * @param count 记录数
     * @return 实体对象列表
     */
    public <T> List<T> query(Class<T> entityClass, Map<String, Object> queryConditions, int start, int count) {
        ParameterAssertor.isNotNull(entityClass, "实体类entityClass不能为空。");
        ParameterAssertor.isTrue(start >= 0, "起始位置start不能小于0。");
        ParameterAssertor.isTrue(count > 0, "记录数count不能小于或等于0。");
        String queryString = buildSelectStatement(entityClass, queryConditions);
        return query(queryString, entityClass, queryConditions, start, count);
    }

    /**
     * 查询指定类型的实体。
     * 
     * @param queryString 查询语句
     * @param entityClass 实体类
     * @param queryConditions 查询条件
     * @return 实体对象列表
     */
    public <T> List<T> query(String queryString, Class<T> entityClass, Map<String, Object> queryConditions) {
        ParameterAssertor.hasText(queryString, "查询语句queryString不能为空。");
        ParameterAssertor.isNotNull(entityClass, "实体类entityClass不能为空。");
        TypedQuery<T> query = entityManager.createQuery(queryString, entityClass);
        setParameters(query, queryConditions);
        return query.getResultList();
    }

    /**
     * 分页查询指定类型的实体。
     * 
     * @param queryString 查询语句
     * @param entityClass 实体类
     * @param queryConditions 查询条件
     * @param start 起始位置
     * @param count 记录数
     * @return 实体对象列表
     */
    public <T> List<T> query(String queryString, Class<T> entityClass, Map<String, Object> queryConditions, int start, int count) {
        ParameterAssertor.hasText(queryString, "查询语句queryString不能为空。");
        ParameterAssertor.isNotNull(entityClass, "实体类entityClass不能为空。");
        ParameterAssertor.isTrue(start >= 0, "起始位置start不能小于0。");
        ParameterAssertor.isTrue(count > 0, "记录数count不能小于或等于0。");
        TypedQuery<T> query = entityManager.createQuery(queryString, entityClass);
        setParameters(query, queryConditions);
        query.setFirstResult(start);
        query.setMaxResults(count);
        return query.getResultList();
    }

    /**
     * 查询实体。
     * 
     * @param queryString 查询语句
     * @param queryConditions 查询条件
     * @return 实体对象列表
     */
    public List<?> query(String queryString, Map<String, Object> queryConditions) {
        ParameterAssertor.hasText(queryString, "查询语句queryString不能为空。");
        Query query = entityManager.createQuery(queryString);
        setParameters(query, queryConditions);
        return query.getResultList();
    }

    /**
     * 分页查询实体。
     * 
     * @param queryString 查询语句
     * @param queryConditions 查询条件
     * @param start 起始位置
     * @param count 记录数
     * @return 实体对象列表
     */
    public List<?> query(String queryString, Map<String, Object> queryConditions, int start, int count) {
        ParameterAssertor.hasText(queryString, "查询语句queryString不能为空。");
        ParameterAssertor.isTrue(start >= 0, "起始位置start不能小于0。");
        ParameterAssertor.isTrue(count > 0, "记录数count不能小于或等于0。");
        Query query = entityManager.createQuery(queryString);
        setParameters(query, queryConditions);
        query.setFirstResult(start);
        query.setMaxResults(count);
        return query.getResultList();
    }

    /**
     * 构建select语句："select count(1) from EntityName where 1=1 and fieldName=:fieldName"
     * 
     * @param entityClass 实体类
     * @param queryConditions 查询套件
     * @return select语句
     */
    private String buildCountStatement(Class<?> entityClass, Map<String, Object> queryConditions) {
        String statement = "select count(1) from {0} where {1}";
        String entityName = entityClass.getName();
        String whereClause = buildWhereClause(queryConditions);
        return MessageFormat.format(statement, entityName, whereClause);
    }

    /**
     * 构建select语句："from EntityName where 1=1 and fieldName=:fieldName"
     * 
     * @param entityClass 实体类
     * @param queryConditions 查询套件
     * @return select语句
     */
    private String buildSelectStatement(Class<?> entityClass, Map<String, Object> queryConditions) {
        String statement = "from {0} where {1}";
        String entityName = entityClass.getName();
        String whereClause = buildWhereClause(queryConditions);
        return MessageFormat.format(statement, entityName, whereClause);
    }

    /**
     * 构建update语句："update EntityName set fieldName1=:fieldName1, fieldName2=:fieldName2 where 1=1 and fieldName=:fieldName"
     * 
     * @param entityClass 实体类
     * @param updateFields 更新字段
     * @param updateConditions 更新条件
     * @return update语句
     */
    private String buildUpdateStatement(Class<?> entityClass, Map<String, Object> updateFields, Map<String, Object> updateConditions) {
        String statement = "update {0} set {1} where {2}";
        String entityName = entityClass.getName();
        String setClause = buildSetClause(updateFields);
        String whereClause = buildWhereClause(updateConditions);
        return MessageFormat.format(statement, entityName, setClause, whereClause);
    }

    /**
     * 构建delete语句："delete from EntityName where 1=1 and fieldName=:fieldName"
     * 
     * @param entityClass 实体类
     * @param deleteConditions 删除条件
     * @return delete语句
     */
    private String buildDeleteStatement(Class<?> entityClass, Map<String, Object> deleteConditions) {
        String statement = "delete from {0} where {1}";
        String entityName = entityClass.getName();
        String whereClause = buildWhereClause(deleteConditions);
        return MessageFormat.format(statement, entityName, whereClause);
    }

    /**
     * 构建set子句："fieldName1=:fieldName1, fieldName2=:fieldName2"
     * 
     * @param updateFields 更新字段
     * @return set子句
     */
    private String buildSetClause(Map<String, Object> updateFields) {
        StringBuffer sbSetClause = new StringBuffer();
        for (String fieldName : updateFields.keySet()) {
            String pattern = sbSetClause.length() == 0 ? "{0}=:{1}" : ", {0}=:{1}";
            String updateField = MessageFormat.format(pattern, fieldName, fieldName);
            sbSetClause.append(updateField);
        }
        return sbSetClause.toString();
    }

    /**
     * 构建where子句："1=1 and fieldName=:fieldName"
     * 
     * @param conditions 条件
     * @return where子句
     */
    private String buildWhereClause(Map<String, Object> conditions) {
        StringBuffer sbWhereClause = new StringBuffer("1=1");
        if (MapUtils.isNotEmpty(conditions)) {
            for (String fieldName : conditions.keySet()) {
                String condition = MessageFormat.format(" and {0}=:{1}", fieldName, fieldName);
                sbWhereClause.append(condition);
            }
        }
        return sbWhereClause.toString();
    }

    /**
     * 设置Query对象的参数值。
     * 
     * @param query Query对象
     * @param parameters 参数值
     */
    private void setParameters(Query query, Map<String, Object> parameters) {
        if (MapUtils.isNotEmpty(parameters)) {
            for (Entry<String, Object> parameter : parameters.entrySet()) {
                query.setParameter(parameter.getKey(), parameter.getValue());
            }
        }
    }

}
