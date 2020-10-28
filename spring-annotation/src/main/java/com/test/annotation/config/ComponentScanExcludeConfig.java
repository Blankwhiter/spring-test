package com.test.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(basePackages = {"com.test.annotation"},excludeFilters = {
        //这里面是一个@Filter注解数组，FilterType.ANNOTATION表示的排除的规则 ：按照注解的方式来进行排除
        //classes = {Controller.class,Service.class}表示的是标有这些注解的类给排除掉
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class, Service.class})
})
public class ComponentScanExcludeConfig {
}
