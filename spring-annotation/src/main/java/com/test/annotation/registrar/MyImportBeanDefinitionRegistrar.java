package com.test.annotation.registrar;

import com.test.annotation.bean.Brain;
import com.test.annotation.bean.Eyes;
import com.test.annotation.bean.Nose;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * 所有需要添加到容器中的bean通过BeanDefinitionRegistry里面的registerBeanDefinition方法来手动的进行注册
 * registerBeanDefinitions可以定义自己的添加逻辑等
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     *
     * AnnotationMetadata 当前类的注解信息
     * BeanDefinitionRegistry BeanDefinition注册类
     *
     * 我们把所有需要添加到容器中的bean通过BeanDefinitionRegistry里面的registerBeanDefinition方法来手动的进行注册
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        //判断IOC容器里面是否含有这头（head）这个组件
        boolean definition = beanDefinitionRegistry.containsBeanDefinition("com.test.annotation.bean.Head");
        //如果有的话，我就把Brain  Eyes Nose 的bean的实例给注册到IOC容器中
        if (definition) {
            //指定bean的定义信息，参数里面指定要注册的bean的类型：RainBow.class
            RootBeanDefinition brain = new RootBeanDefinition(Brain.class);
            RootBeanDefinition eyes = new RootBeanDefinition(Eyes.class);
            RootBeanDefinition nose = new RootBeanDefinition(Nose.class);
            //注册bean，并且指定bean名
            beanDefinitionRegistry.registerBeanDefinition("brain", brain );
            beanDefinitionRegistry.registerBeanDefinition("eyes", eyes );
            beanDefinitionRegistry.registerBeanDefinition("nose", nose );
        }
    }
}
