package test;

import com.test.aop.config.AopConfig;
import com.test.aop.function.AopFunction;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {

    @Test
    public void testAop(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);
        AopFunction aopFunction = (AopFunction) applicationContext.getBean("aopFunction");
        aopFunction.testAop("belonghuang","belonghuang@126.com");

    }
}
