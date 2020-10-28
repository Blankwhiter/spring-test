package com.test.annotation.config;

import com.test.annotation.filter.CustomFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(basePackages = {"com.test.annotation"},includeFilters = {
        //这里面是一个@Filter注解数组，FilterType.CUSTOM表示自定义的排除的规则 ：按照CustomFilter的接口实现符合规则的方式来进行判断
        //classes = {CustomFilter.class}表示的是包含标有符合规则的类
        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {CustomFilter.class})
},useDefaultFilters = false)
public class ComponentScanCustomIncludeConfig {
}
