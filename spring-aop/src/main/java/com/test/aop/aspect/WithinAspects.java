package com.test.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class WithinAspects {

    /**
     * com.test.aop.service包下所有类的所有的方法都会执行(不包含子包) aop方法
     */
    @Pointcut("within(com.test.aop.service.*)")
    public void pointCut(){}

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("[WithinAspects] " + joinPoint.getSignature().getName() + " Before...参数列表是：" + Arrays.asList(joinPoint.getArgs()));
    }

    /**
     * com.test.aop.service包下所有的方法都会执行(包含子包)aop 方法
     */
    @Pointcut("within(com.test.aop.service..*)")
    public void pointCut1(){}

    @Before("pointCut1()")
    public void logStart1(JoinPoint joinPoint) {
        System.out.println("[WithinAspects] " + joinPoint.getSignature().getName() + " Before...参数列表是：" + Arrays.asList(joinPoint.getArgs()));
    }

}
