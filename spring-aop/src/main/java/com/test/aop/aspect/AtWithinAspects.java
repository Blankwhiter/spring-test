package com.test.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class AtWithinAspects {

    /**
     * 目标(target)如果有Service注解的所有方法
     */
    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void pointCut(){}

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("[ @TargetAspects ] " + joinPoint.getSignature().getName() + " Before...参数列表是：" + Arrays.asList(joinPoint.getArgs()));
    }
}
