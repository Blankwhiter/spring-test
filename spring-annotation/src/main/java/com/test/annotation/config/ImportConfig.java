package com.test.annotation.config;

import com.test.annotation.bean.Head;
import com.test.annotation.bean.Heart;
import com.test.annotation.bean.Person;
import com.test.annotation.registrar.MyImportBeanDefinitionRegistrar;
import com.test.annotation.selector.MyImportSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *  配置类就等同以前的配置文件
 *  注解Configuration告诉Spring这是一个配置类
 */
@Configuration
//快速导入组件，id默认是组件的全类名.导入的三种方式 1.类，2.实现ImportSelector的类，3.实现ImportBeanDefinitionRegistrar的类
@Import({Head.class, Heart.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class ImportConfig {

    /**
     * 给容器中注册组件：
     * 1）、扫描+组件标注注解（@Controller/@Service/@Repository/@Component）
     * 【局限于要求是自己写的类，如果导入的第三方没有添加这些注解，那么就注册不上了】
     *
     * 2）、@Bean[导入的第三方包里面的组件]
     * 3）、@Import[快速的给容器中导入一个组件]
     *      （1）、 @Import(要导入容器中的组件);容器中就会自动的注册这个组件，id默认是全类名
     *      （2）、 ImportSelector ：返回需要的组件的全类名的数组；
     */

    //相当于xml配置文件中的<bean>标签，告诉容器注册一个bean
    //之前xml文件中<bean>标签有bean的class类型，那么现在注解方式的类型当然也就是返回值的类型
    //之前xml文件中<bean>标签有bean的id，现在注解的方式默认用的是方法名来作为bean的id
    @Bean
    public Person person(){
        //这个的bean名称是person
        return new Person("person","北京",19);
    }

}
