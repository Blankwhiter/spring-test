package com.test.aop.config;

import com.test.aop.aspect.AtWithinAspects;
import com.test.aop.service.impl.TestServiceImpl;
import com.test.aop.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AtWithinConfig {

    @Bean
    public AtWithinAspects atWithinAspects(){
        return new AtWithinAspects();
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
