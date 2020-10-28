package com.test.annotation.filter;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * 自定义过滤 供@ComponentScan.Filter的type使用
 */
public class CustomFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取到当前类注解的信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前类的资源的信息（比如类的路径）
        Resource resource = metadataReader.getResource();

        //获取到当前正在扫描的类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        String className = classMetadata.getClassName();
        //表示 dao包下 才符合条件
        String packageName = "com.test.annotation.dao";
        if (className.contains(packageName)) {
            System.out.println("符合条件的是 "+className);
            return true;
        }
        return false;
    }
}
