package com.test.annotation.config;

import com.test.annotation.bean.Computer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 使用@PropertySource读取外部配置文件中的key/value保存到运行的环境变量中
 *  加载完外部配置文件以后使用${}取出配置文件的值
 */
@PropertySource(value = {"classpath:computer.properties"})
@Configuration
public class ValuePropertySourceConfig {

    @Bean
    public Computer computer(){
        return new Computer();
    }
}
