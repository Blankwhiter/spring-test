package com.test.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class AtTargetAspects {

    /**
     * 目标(target)使用了@Service注解的方法
     */
    @Pointcut("@target(org.springframework.stereotype.Service)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("[ @TargetAspects ] " + joinPoint.getSignature().getName() + " Before...参数列表是：" + Arrays.asList(joinPoint.getArgs()));
    }

}
