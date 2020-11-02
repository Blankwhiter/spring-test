package com.test.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class AtArgsAspects {

    /**
     * 目标(target)有且仅有一个参数并且参数上类型上有LogParam注解
     */
    @Pointcut("@args(com.test.aop.annotion.LogParam)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("[ @ArgsAspects ] " + joinPoint.getSignature().getName() + " Before...参数列表是：" + Arrays.asList(joinPoint.getArgs()));
    }


}
