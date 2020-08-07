/**
 * 
 */
package com.sunsharing.xshare.framework.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.sunsharing.xshare.framework.core.collection.ArrayUtils;
import com.sunsharing.xshare.framework.db.EnableDatabaseAccess;

/**
 * DAO配置类选择器。
 *
 * @author Kison 2017年5月10日
 */
public class DaoConfigurationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableDatabaseAccess.class.getName());
        DaoType[] daoTypes = (DaoType[]) annotationAttributes.get("daoType");
        return getDaoConfigurationClassNames(daoTypes);
    }

    /**
     * 获取DAO配置类。
     * 
     * @param daoTypes DAO类型
     * @return DAO配置类
     */
    private String[] getDaoConfigurationClassNames(DaoType[] daoTypes) {
        List<String> daoConfigurationClassNames = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(daoTypes)) {
            for (DaoType daoType : daoTypes) {
                daoConfigurationClassNames.add(daoType.getDaoConfigurationClassName());
            }
        }
        return daoConfigurationClassNames.toArray(new String[daoConfigurationClassNames.size()]);
    }

}
