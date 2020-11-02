package com.test.aop.config;

import com.test.aop.aspect.AtTargetAspects;
import com.test.aop.po.User;
import com.test.aop.service.impl.TestServiceImpl;
import com.test.aop.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AtTargetConfig {

    @Bean
    public AtTargetAspects atTargetAspects(){
        return new AtTargetAspects();
    }

    @Bean
    public TestServiceImpl testService(){
        return new TestServiceImpl();
    }

    @Bean
    public UserServiceImpl userService(){
        return new UserServiceImpl();
    }

}
