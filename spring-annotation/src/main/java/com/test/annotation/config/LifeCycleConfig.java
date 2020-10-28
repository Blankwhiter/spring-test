package com.test.annotation.config;

import com.test.annotation.bean.PersonLifeCycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.test.annotation.processor")
public class LifeCycleConfig {

    //单例情况
    @Bean(initMethod = "myInit",destroyMethod = "myDestroy")
    public PersonLifeCycle personLifeCycle(){
        System.out.println("[1] personLifeCycle bean 初始化");
        return new PersonLifeCycle("belonghuang","福州",21);
    }

    //多例情况
    @Scope("prototype")
    @Bean(initMethod = "myInit",destroyMethod = "myDestroy",value = "personLifeCycleScope")
    public PersonLifeCycle personLifeCycleScope(){
        System.out.println("[1] personLifeCycleScope 初始化");
        return new PersonLifeCycle("belonghuang","福州",20);
    }

}
