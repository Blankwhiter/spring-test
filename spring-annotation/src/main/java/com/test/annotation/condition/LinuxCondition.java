package com.test.annotation.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
/**
 * 判断操作系统是否为Linux系统
 */
public class LinuxCondition implements Condition {
    /**
     *  ConditionContext : 判断条件能使用的上下文(环境)
     *  AnnotatedTypeMetadata : 注释信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //判断是否为Linux系统
        //1.能获取到IOC容器里面的BeanFactory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();

        //2.获取类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();

        //3.能获取当前环境的信息
        Environment environment = conditionContext.getEnvironment();

        //4.获取到bean定义的注册类
        BeanDefinitionRegistry registry = conditionContext.getRegistry();

        //获取操作系统
        String property = environment.getProperty("os.name");
        if (property!=null && property.contains("linux")) {
            return true;
        }
        return false;
    }
}
