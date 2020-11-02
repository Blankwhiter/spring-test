package com.test.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class ThisAspects {

    /**
     * /实现了该接口的类、继承该类、该类本身的类---的所有方法（包括不是接口定义的方法，但不包含父类的方法）都会执行aop方法
     */
    @Pointcut("this(com.test.aop.service.TestService)")
    public void pointCut(){}

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("[ThisAspects] " + joinPoint.getSignature().getName() + " Before...参数列表是：" + Arrays.asList(joinPoint.getArgs()));
    }

    @Pointcut("this(com.test.aop.po.Child)")
    public void pointCut1(){}

    @Before("pointCut1()")
    public void logStart1(JoinPoint joinPoint) {
        System.out.println("[ThisAspects] " + joinPoint.getSignature().getName() + " Before...参数列表是：" + Arrays.asList(joinPoint.getArgs()));
    }



}
