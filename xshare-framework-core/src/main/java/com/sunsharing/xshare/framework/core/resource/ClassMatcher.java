/**
 * 
 */
package com.sunsharing.xshare.framework.core.resource;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;

/**
 * Class匹配器。<br>
 * <p>
 *
 * 参考：<br>
 * org.springframework.core.type.ClassMetadata<br>
 * org.springframework.core.type.AnnotationMetadata<br>
 *
 * @author Kison 2017年4月1日
 */
public interface ClassMatcher {

    /**
     * 判断指定Class是否匹配。
     * 
     * @param classMetadata Class的元数据对象
     * @param annotationMetadata Class的注解元数据对象
     * @return 如果指定Class匹配，返回true；否则返回false
     */
    boolean match(ClassMetadata classMetadata, AnnotationMetadata annotationMetadata);

}
