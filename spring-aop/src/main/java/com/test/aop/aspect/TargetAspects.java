package com.test.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class TargetAspects {

    /**
     * 实现了该接口的类、继承该类、该类本身的类---的所有方法（包括不是接口定义的方法，包含父类的方法）
     */
    @Pointcut("target(com.test.aop.po.Child)")
    public void pointCut(){}


    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("[TargetAspects] " + joinPoint.getSignature().getName() + " Before...参数列表是：" + Arrays.asList(joinPoint.getArgs()));
    }
}
