package com.test.aop.config;

import com.test.aop.aspect.AtAnnotationAspects;
import com.test.aop.po.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AtAnnotationConfig {


    @Bean
    public User user(){
        return new User();
    }

    @Bean
    public AtAnnotationAspects atAnnotationAspects(){
        return new AtAnnotationAspects();
    }


}
