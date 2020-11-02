package com.test.aop.config;

import com.test.aop.aspect.WithinAspects;
import com.test.aop.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
public class WithinConfig {


    @Bean
    public UserServiceImpl userService(){
        return new UserServiceImpl();
    }

    @Bean
    public WithinAspects withinAspects(){
        return new WithinAspects();
    }
}
