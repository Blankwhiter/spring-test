package com.test.annotation.selector;

import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义逻辑返回多个需要导入的组件
 */
public class MyImportSelector implements org.springframework.context.annotation.ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //将手和脚一起导入进去
        return new String[]{"com.test.annotation.bean.Hands","com.test.annotation.bean.Legs"};
    }
}
