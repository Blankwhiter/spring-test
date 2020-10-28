package com.test.annotation.config;

import com.test.annotation.bean.Person;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 *  配置类就等同以前的配置文件
 *  注解Configuration告诉Spring这是一个配置类
 */
@Configuration
public class AnnotationConfig {

    //相当于xml配置文件中的<bean>标签，告诉容器注册一个bean
    //之前xml文件中<bean>标签有bean的class类型，那么现在注解方式的类型当然也就是返回值的类型
    //之前xml文件中<bean>标签有bean的id，现在注解的方式默认用的是方法名来作为bean的id
    @Bean
    public Person person(){
        //这个的bean名称是person
        return new Person("person","北京",19);
    }

    @Bean
    public Person person01(){
        //这个的bean名称是person01(默认方法名)
        return new Person("person01","上海",20);
    }

    //还可以通过value来指定名称
    @Bean(value = "customerPerson")
    public Person person02(){
        //这个的bean名称是customerPerson
        return new Person("customerPerson","广州",21);
    }

    //singleton:单实例的
    //prototype:多实例的
    //request:同一次请求创建一个实例
    //session:同一个session创建的一个实例
    @Scope("prototype")
    @Bean
    public Person personScope(){
        //这个的bean名称是personScope
        return new Person("personScope","北京",19);
    }

}
