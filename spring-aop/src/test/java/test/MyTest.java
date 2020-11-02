package test;

import com.test.aop.config.*;
import com.test.aop.function.AopFunction;
import com.test.aop.po.Child;
import com.test.aop.po.User;
import com.test.aop.po.UserParam;
import com.test.aop.service.BaseService;
import com.test.aop.service.TestService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {

    @Test
    public void testAop() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
        AopFunction aopFunction = (AopFunction) applicationContext.getBean("aopFunction");
        aopFunction.testAop("belonghuang", "belonghuang@126.com");
    }


    @Test
    public void testExecutionAspects1() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExecutionConfig.class);
        AopFunction aopFunction = (AopFunction) applicationContext.getBean("aopFunction");
        aopFunction.testAop("belonghuang", "belonghuang@126.com");
        aopFunction.testMethodExecution("execution(* testMethodExecution(..))");
    }


    @Test
    public void testExecutionAspects2() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExecutionConfig.class);
        AopFunction aopFunction = (AopFunction) applicationContext.getBean("aopFunction");
        aopFunction.testMethodExecution(100, "execution(* testMethodExecution(..))");
    }


    @Test
    public void testExecutionAspects3() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExecutionConfig.class);
        AopFunction aopFunction = (AopFunction) applicationContext.getBean("aopFunction");
        aopFunction.testMethodExecution(25.5, "execution(* testMethodExecution(..))");
    }


    @Test
    public void testExecutionAspects4() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExecutionConfig.class);
        AopFunction aopFunction = (AopFunction) applicationContext.getBean("aopFunction");
        aopFunction.testMethodExecution(true);
    }


    @Test
    public void testExecutionAspects5() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExecutionConfig.class);
        AopFunction aopFunction = (AopFunction) applicationContext.getBean("aopFunction");
        aopFunction.testMethodExecution(2.01f);
    }

    @Test
    public void testExecutionAspects6() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(WithinConfig.class);
        BaseService userService = (BaseService) applicationContext.getBean("userService");
        userService.baseMethod();
    }

    @Test
    public void testExecutionAspects7() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ThisConfig.class);
        BaseService userService = (BaseService) applicationContext.getBean("userService");
        userService.baseMethod();
        System.out.println("------------------------------------------------------------------");
        TestService testService = (TestService) applicationContext.getBean("testService");
        testService.testMethod();
        System.out.println("------------------------------------------------------------------");
        Child child = (Child) applicationContext.getBean("child");
        child.run();
        System.out.println("------------------------------------------------------------------");
        child.show();
    }

    @Test
    public void testExecutionAspects8() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TargetConfig.class);
        Child child = (Child) applicationContext.getBean("child");
        child.run();
        System.out.println("------------------------------------------------------------------");
        child.show();
    }


    @Test
    public void testExecutionAspects9() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AtTargetConfig.class);
        BaseService userService = (BaseService) applicationContext.getBean("userService");
        userService.baseMethod();
        System.out.println("------------------------------------------------------------------");
        TestService testService = (TestService) applicationContext.getBean("testService");
        testService.testMethod();
    }


    @Test
    public void testExecutionAspects10() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AtAnnotationConfig.class);
        User user = (User) applicationContext.getBean("user");
        user.show();
        System.out.println("------------------------------------------------------------------");
        user.run();
    }


    @Test
    public void testExecutionAspects11() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AtWithinConfig.class);
        BaseService userService = (BaseService) applicationContext.getBean("userService");
        userService.baseMethod();
        System.out.println("------------------------------------------------------------------");
        TestService testService = (TestService) applicationContext.getBean("testService");
        testService.testMethod();
    }


    @Test
    public void testExecutionAspects12() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AtArgsConfig.class);
        User user = (User) applicationContext.getBean("user");
        UserParam userParam = new UserParam();
        userParam.setName("belonghuang");
        user.config(userParam);
        System.out.println("------------------------------------------------------------------");
        user.test(" method2 ");
    }
}
