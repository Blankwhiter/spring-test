package com.test.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class AtAnnotationAspects {

    /**
     * 目标(target)任何方法有Log注解的方法
     */
    @Pointcut("@annotation(com.test.aop.annotion.Log)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("[@AnnotationAspects] " + joinPoint.getSignature().getName() + " Before...参数列表是：" + Arrays.asList(joinPoint.getArgs()));
    }


}
