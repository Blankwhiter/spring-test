package com.test.aop.config;

import com.test.aop.aspect.ExecutionAspects;
import com.test.aop.aspect.LogAspects;
import com.test.aop.aspect.TimeAspects;
import com.test.aop.function.AopFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;


/**
 * AOP:【动态代理】
 * 能在程序运行期间动态的将某段代码片段切入到指定的方法指定位置进行运行的编程方式；
 * <p>
 * 1、导入aop模块，Spring AOP：(spring-aspects)
 * 2、定义一个业务逻辑类（AopFunction），在业务逻辑运行的时候将日志进行打印（方法之前、方法运行结束、包括方法出现异常等等）
 * 3、定义一个日志切面类（LogAspects）：切面类里面的方法需要动态感知AopFunction.testAop运行到哪里了然后执行
 * 切面类里面的方法就是通知方法：
 * （1）前置通知（@Before）：logStart，在目标方法（testAop）运行之前运行
 * （2）后置通知（@After）：logEnd，在目标方法（testAop）运行之前运行
 * （3）返回通知（@AfterReturning）：logReturn，在目标方法（testAop）执行返回（无论是正常返回还是异常返回）之后运行
 * （4）异常通知（@AfterThrowing）：logException，在目标方法（testAop）出现异常之后运行
 * （5）环绕通知（@Around）：动态代理，手动推进目标方法运行（joinPoint.proceed()）
 * 4、给切面类的目标方法标注何时何地运行（通知注解）
 * 5、将切面类和业务逻辑类（目标方法所在的类）都加入到容器中；
 * 6、必须要告诉Spring哪一个类是切面类（只要给切面类上加上一个注解：@Aspect）
 * 7、给配置类中加入@EnableAspectJAutoProxy：开启基于注解的aop模式
 */
@Configuration
@EnableAspectJAutoProxy
//当使用@Import注解 插入多个切面时， 按照插入的依次顺序作为排列优先级顺序
//@Import({TimeAspects.class, LogAspects.class})
public class AopConfig {

    @Bean
    public AopFunction aopFunction() {
        return new AopFunction();
    }

    /**
     * 这里用Order来确定执行顺序 ，但是由于LogAspects与TimeAspects 针对同一个pointcut所以并不生效
     * @return
     */
    @Order(2)
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }

    @Order(1)
    @Bean
    public TimeAspects timeAspects(){
        return new TimeAspects();
    }

}
