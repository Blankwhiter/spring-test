package com.test.annotation.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowsCondition  implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //3.能获取当前环境的信息
        Environment environment = context.getEnvironment();
        //获取操作系统
        String property = environment.getProperty("os.name");
        if (property!=null && property.contains("windows")) {
            return true;
        }
        return false;
    }
}
