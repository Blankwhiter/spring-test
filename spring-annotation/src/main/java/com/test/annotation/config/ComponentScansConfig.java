package com.test.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
//ComponentScans java环境需要在jdk8以上
@ComponentScans(value = {
        @ComponentScan(basePackages = {"com.test.annotation"}, excludeFilters = {
                //这里面是一个@Filter注解数组，FilterType.ANNOTATION表示的排除的规则 ：按照注解的方式来进行排除
                //classes = {Controller.class,Service.class}表示的是标有这些注解的类给排除掉
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = { Service.class})
        }),
        @ComponentScan(basePackages = {"com.test.annotation"},includeFilters = {
        //这里面是一个@Filter注解数组，FilterType.ANNOTATION表示的排除的规则 ：按照注解的方式来进行过滤
        //classes = {Controller.class,Repository.class}表示的是过滤包含标有这些注解的类
        //useDefaultFilters = false 默认会将所有的注解扫描，关闭默认。
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Repository.class})
        },useDefaultFilters = false)
})
public class ComponentScansConfig {
}
