package com.test.annotation.config;

import com.test.annotation.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 *  配置类就等同以前的配置文件
 *  注解Configuration告诉Spring这是一个配置类
 */
@Configuration
public class AnnotationLazyConfig {

    //相当于xml配置文件中的<bean>标签，告诉容器注册一个bean
    //之前xml文件中<bean>标签有bean的class类型，那么现在注解方式的类型当然也就是返回值的类型
    //之前xml文件中<bean>标签有bean的id，现在注解的方式默认用的是方法名来作为bean的id
    @Bean
    public Person person(){
        //这个的bean名称是person
        System.out.println("person创建开始");
        return new Person("person","北京",19);
    }


    /**
     * 懒加载：是专门针对于单实例的bean的
     *       1.单实例的bean：默认是在容器启动的时候创建对象；
     *       2.懒加载：容器启动的时候，不创建对象，而是在第一次使用（获取）Bean的时候来创建对象，并进行初始化
     */
    @Lazy
    @Bean
    public Person personLazy(){
        //这个的bean名称是personLazy
        System.out.println("personLazy创建开始");
        return new Person("personLazy","北京",22);
    }
}
