package com.test.annotation.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ImportCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //3.能获取当前环境的信息
        Environment environment = context.getEnvironment();
        //获取import参数
        String property = environment.getProperty("import");
        if (property!=null && property.contains("true")) {
            return true;
        }
        return false;
    }

}
