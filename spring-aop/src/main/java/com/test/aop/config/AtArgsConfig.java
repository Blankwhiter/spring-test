package com.test.aop.config;

import com.test.aop.aspect.AtArgsAspects;
import com.test.aop.po.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AtArgsConfig {


    @Bean
    public User user(){
        return new User();
    }

    @Bean
    public AtArgsAspects atArgsAspects(){
        return new AtArgsAspects();
    }


}
