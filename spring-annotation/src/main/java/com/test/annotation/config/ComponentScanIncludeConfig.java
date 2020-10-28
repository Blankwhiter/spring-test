package com.test.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(basePackages = {"com.test.annotation"},includeFilters = {
        //这里面是一个@Filter注解数组，FilterType.ANNOTATION表示的排除的规则 ：按照注解的方式来进行过滤
        //classes = {Controller.class,Repository.class}表示的是过滤包含标有这些注解的类
        //useDefaultFilters = false 默认会将所有的注解扫描，关闭默认。
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class, Repository.class})
},useDefaultFilters = false)
public class ComponentScanIncludeConfig {
}
