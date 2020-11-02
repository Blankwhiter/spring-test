package com.test.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

/**
 * 这里就测试 @Before 方法执行前，其他@After等类似 不再赘述
 */
@Aspect
public class ExecutionAspects {

    /**
     * 任意方法名为testMethodExecution的切面
     */
    @Pointcut("execution(* testMethodExecution(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println("[ExecutionAspects] " + joinPoint.getSignature().getName() + " Before...参数列表是：" + Arrays.asList(joinPoint.getArgs()));
    }

    /**
     * 任意方法名为testMethodExecution，且返回类型为int 的切面
     */
    @Pointcut("execution(int testMethodExecution(..))")
    public void pointCut1(){}

    @Before("pointCut1()")
    public void logStart1(JoinPoint joinPoint) {
        System.out.println("[ExecutionAspects] " + joinPoint.getSignature().getName() + " Before...参数列表是：" + Arrays.asList(joinPoint.getArgs()));
    }

    /**
     * 任意方法名为testMethodExecution，且参数类型为Double 和 String 的切面
     */
    @Pointcut("execution(* testMethodExecution(Double,String))")
    public void pointCut2(){}

    @Before("pointCut2()")
    public void logStart2(JoinPoint joinPoint) {
        System.out.println("[ExecutionAspects] " + joinPoint.getSignature().getName() + " Before...参数列表是：" + Arrays.asList(joinPoint.getArgs()));
    }

    /**
     * 任意方法名为testMethodExecution，且参数类型为Boolean 的切面
     */
    @Pointcut("execution(* testMethodExecution(Boolean))")
    public void pointCut3(){}


    /**
     * 任意方法名为testMethodExecution，且参数类型为Float 的切面
     */
    @Pointcut("execution(* testMethodExecution(Float))")
    public void pointCut4(){}

    /**
     * 当满足pointCut3 或者 pointCut4 条件
     * @param joinPoint
     */
    @Before("pointCut3() || pointCut4()")
    public void logStart4(JoinPoint joinPoint) {
        System.out.println("[ExecutionAspects] " + joinPoint.getSignature().getName() + " Before...参数列表是：" + Arrays.asList(joinPoint.getArgs()));
    }

}
