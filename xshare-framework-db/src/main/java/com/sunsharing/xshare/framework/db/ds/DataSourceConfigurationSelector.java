/**
 * 
 */
package com.sunsharing.xshare.framework.db.ds;

import java.util.Map;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.sunsharing.xshare.framework.db.EnableDatabaseAccess;

/**
 * 数据源配置类选择器。
 *
 * @author Kison 2017年5月10日
 */
public class DataSourceConfigurationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableDatabaseAccess.class.getName());
        DataSourceType dataSourceType = (DataSourceType) annotationAttributes.get("dataSourceType");
        return getDataSourceConfigurationClassNames(dataSourceType);
    }

    /**
     * 获取数据源配置类。
     * 
     * @param dataSourceType 数据源类型
     * @return 数据源配置类
     */
    private String[] getDataSourceConfigurationClassNames(DataSourceType dataSourceType) {
        String dataSourceConfigurationClassName = dataSourceType.getDataSourceConfigurationClassName();
        return new String[] { dataSourceConfigurationClassName };
    }

}
