package com.test.aop.config;

import com.test.aop.aspect.ThisAspects;
import com.test.aop.po.Child;
import com.test.aop.po.Parent;
import com.test.aop.service.impl.TestServiceImpl;
import com.test.aop.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class ThisConfig {

    @Bean
    public UserServiceImpl userService(){
        return new UserServiceImpl();
    }


    @Bean
    public TestServiceImpl testService(){
        return new TestServiceImpl();
    }

    @Bean
    public Parent parent(){
        return new Parent();
    }

    @Bean
    public Child child(){
        return new Child();
    }

    @Bean
    public ThisAspects thisAspects() {
        return new ThisAspects();
    }
}
