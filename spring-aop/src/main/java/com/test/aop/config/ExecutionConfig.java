package com.test.aop.config;

import com.test.aop.aspect.ExecutionAspects;
import com.test.aop.function.AopFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class ExecutionConfig {

    @Bean
    public AopFunction aopFunction() {
        return new AopFunction();
    }

    @Bean
    public ExecutionAspects executionAspects() {
        return new ExecutionAspects();
    }
}
