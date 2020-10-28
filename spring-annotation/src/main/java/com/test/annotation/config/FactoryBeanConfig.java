package com.test.annotation.config;

import com.test.annotation.factorybean.PersonFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryBeanConfig {

    /**
     * 使用Spring提供的FactoryBean（工厂bean）
     *            （1）、默认获取到的是工厂bean调用getObject创建的对象
     *            （2）、要获取工厂bean本身，我们需要给id前面加上一个“&”符号：&colorFactoryBean
     * @return
     */
    @Bean
    public PersonFactoryBean personFactoryBean(){
        return new PersonFactoryBean();
    }
}
