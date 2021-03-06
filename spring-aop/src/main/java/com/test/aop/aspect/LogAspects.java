package com.test.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class LogAspects {

    /**
     * 抽取公共的切入点表达式
     */
    @Pointcut("execution(* com.test.aop.function..*.*(..))")
    public void pointCut() {
    }

    //@Before在目标方法之前切入：切入点表达式（指定在哪个方法切入）
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
//        int a = 1/0; //供logException调试使用
        System.out.println("[LogAspects] " + joinPoint.getSignature().getName() + " Before...参数列表是：" + Arrays.asList(joinPoint.getArgs()));
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("[LogAspects] " + joinPoint.getSignature().getName() + " After...参数列表是：" + Arrays.asList(joinPoint.getArgs()));
    }

    /**
     * 如果我们要写JoinPoint这个参数，那么这个参数一定要写在参数列表的第一位；
     *
     * @param result
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println("[LogAspects] " + joinPoint.getSignature().getName() + " AfterReturning 正常返回...运行结果为：" + result);
    }

    /**
     * 如果我们要写JoinPoint这个参数，那么这个参数一定要写在参数列表的第一位；
     *
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println("[LogAspects] " + joinPoint.getSignature().getName() + " AfterThrowing异常...异常信息为：" + exception.getMessage());
    }


    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) {
        System.out.println("[LogAspects] Around  begin");
        Object object = null;
        try {
            object = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("[LogAspects] Around  end");
        return object;
    }
}
