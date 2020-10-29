# spring  测试
![注解驱动开发](https://img-blog.csdnimg.cn/20201028094850197.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JlbG9uZ2h1YW5nMTU3NDA1,size_16,color_FFFFFF,t_70#pic_center)

注解驱动开发 ===>  spring-annotation模块，MyTest类测试

说明：
- @Component ：标准一个普通的spring Bean类。
- @Repository：标注一个DAO组件类。
- @Service：标注一个业务逻辑组件类。
- @Controller：标注一个控制器组件类。

这些都是注解在平时的开发过程中出镜率极高，@Component、@Repository、@Service、@Controller实质上属于同一类注解，用法相同，功能相同，区别在于标识组件的类型。
@Component可以代替@Repository、@Service、@Controller，因为这三个注解是被@Component标注的。

1、被注解的java类当做Bean实例，Bean实例的名称默认是Bean类的首字母小写，其他部分不变。@Service也可以自定义Bean名称，但是必须是唯一的！ 
2、尽量使用对应组件注解的类替换@Component注解，在spring未来的版本中，@Controller，@Service，@Repository会携带更多语义。并且便于开发和维护！
3、指定了某些类可作为Spring Bean类使用后，最好还需要让spring搜索指定路径


Autowired：属于Spring 的org.springframework.beans.factory.annotation包下,可用于为类的属性、构造器、方法进行注值
@Resource：不属于spring的注解，而是来自于JSR-250位于java.annotation包下，使用该annotation为目标bean指定协作者Bean。
@PostConstruct 和 @PreDestroy 方法 实现初始化和销毁bean之前进行的操作
 
 @Resource 与 @Autowired 异同点：
（1）：相同点 @Resource的作用相当于@Autowired，均可标注在字段或属性的setter方法上。 （2）：不同点
a：提供方 @Autowired是Spring的注解，@Resource是javax.annotation注解，而是来自于JSR-250，J2EE提供，需要JDK1.6及以上。
b ：注入方式 @Autowired只按照Type 注入；@Resource默认按Name自动注入，也提供按照Type 注入；
c：属性
@Autowired注解可用于为类的属性、构造器、方法进行注值。默认情况下，其依赖的对象必须存在（bean可用），如果需要改变这种默认方式，可以设置其required属性为false。
还有一个比较重要的点就是，@Autowired注解默认按照类型装配，如果容器中包含多个同一类型的Bean，那么启动容器时会报找不到指定类型bean的异常，解决办法是结合**@Qualifier**注解进行限定，指定注入的bean名称。
@Resource有两个中重要的属性：name和type。name属性指定byName，如果没有指定name属性，当注解标注在字段上，即默认取字段的名称作为bean名称寻找依赖对象，当注解标注在属性的setter方法上，即默认取属性名作为bean名称寻找依赖对象。
需要注意的是，@Resource如果没有指定name属性，并且按照默认的名称仍然找不到依赖对象时， @Resource注解会回退到按类型装配。但一旦指定了name属性，就只能按名称装配了。
d：@Resource注解的使用性更为灵活，可指定名称，也可以指定类型 ；@Autowired注解进行装配容易抛出异常，特别是装配的bean类型有多个的时候，而解决的办法是需要在增加@Qualifier进行限定


@Component 和 @Configuration
```java
@Configuration
public static class Config {

    @Bean
    public SimpleBean simpleBean() {
        return new SimpleBean();
    }

    @Bean
    public SimpleBeanConsumer simpleBeanConsumer() {
        return new SimpleBeanConsumer(simpleBean());
    }
}

```

```java
@Component
public static class Config {

    @Bean
    public SimpleBean simpleBean() {
        return new SimpleBean();
    }

    @Bean
    public SimpleBeanConsumer simpleBeanConsumer() {
        return new SimpleBeanConsumer(simpleBean());
    }
}

```
第一个代码正常工作，正如预期的那样，SimpleBeanConsumer将会得到一个单例SimpleBean的链接。
第二个配置是完全错误的，因为Spring会创建一个SimpleBean的单例bean，
但是SimpleBeanConsumer将获得另一个SimpleBean实例（也就是相当于直接调用new SimpleBean() ，这个bean是不归Spring管理的），既new SimpleBean() 实例是Spring上下文控件之外的。


使用@ configuration，所有标记为@ bean的方法将被包装成一个CGLIB包装器，它的工作方式就好像是这个方法的第一个调用，那么原始方法的主体将被执行，最终的对象将在spring上下文中注册。所有进一步的调用只返回从上下文检索的bean。
在上面的第二个代码块中，新的SimpleBeanConsumer(simpleBean())只调用一个纯java方法。为了纠正第二个代码块，我们可以这样做
```java
@Component
public static class Config {
    @Autowired
    SimpleBean simpleBean;

    @Bean
    public SimpleBean simpleBean() {
        return new SimpleBean();
    }

    @Bean
    public SimpleBeanConsumer simpleBeanConsumer() {
        return new SimpleBeanConsumer(simpleBean);
    }
}

```

FactoryBean的作用
一般情况下，Spring通过反射机制利用<bean>的class属性指定实现类实例化Bean，在某些情况下，实例化Bean过程比较复杂，如果按照传统的方式，则需要在<bean>中提供大量的配置信息。
配置方式的灵活性是受限的，这时采用编码的方式可能会得到一个简单的方案。Spring为此提供了一个org.springframework.bean.factory.FactoryBean的工厂类接口，用户可以通过实现该接口定制实例化Bean的逻辑。



![bean的生命周期](https://img-blog.csdnimg.cn/20201029135127553.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JlbG9uZ2h1YW5nMTU3NDA1,size_16,color_FFFFFF,t_70#pic_center)
Bean的完整生命周期经历了各种方法调用，这些方法可以划分为以下几类：

1、Bean自身的方法　　：　　这个包括了Bean本身调用的方法和通过配置文件中<bean>的init-method和destroy-method指定的方法

2、Bean级生命周期接口方法　　：　　这个包括了BeanNameAware、BeanFactoryAware、InitializingBean和DiposableBean这些接口的方法

3、容器级生命周期接口方法　　：　　这个包括了InstantiationAwareBeanPostProcessor 和 BeanPostProcessor 这两个接口实现，一般称它们的实现类为“后处理器”。

4、工厂后处理器接口方法　　：　　这个包括了AspectJWeavingEnabler, ConfigurationClassPostProcessor, CustomAutowireConfigurer等等非常有用的工厂后处理器　　接口的方法。工厂后处理器也是容器级的。在应用上下文装配配置文件之后立即调用。





AOP



表达式
`
execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)throws-pattern?)
`
其中后面跟着“?”的是可选项
括号中各个pattern分别表示：
```
修饰符匹配（modifier-pattern?）
返回值匹配（ret-type-pattern）：   可以为*表示任何返回值, 全路径的类名等
类路径匹配（declaring-type-pattern?）
方法名匹配（name-pattern）：可以指定方法名 或者 *代表所有, set* 代表以set开头的所有方法
参数匹配（(param-pattern)）：可以指定具体的参数类型，多个参数间用“,”隔开，各个参数也可以用"*" 来表示匹配任意类型的参数，".."表示零个或多个任意参数。
如(String)表示匹配一个String参数的方法；(*,String) 表示匹配有两个参数的方法，第一个参数可以是任意类型，而第二个参数是String类型。
异常类型匹配（throws-pattern?）
```



由下列方式来定义或者通过 &&、 ||、 !、 的方式进行组合：
- execution：用于匹配方法执行的连接点；
- within：用于匹配指定类型内的方法执行；
- this：用于匹配当前AOP代理对象类型的执行方法；注意是AOP代理对象的类型匹配，这样就可能包括引入接口也类型匹配；        
- target：用于匹配当前目标对象类型的执行方法；注意是目标对象的类型匹配，这样就不包括引入接口也类型匹配；
- args：用于匹配当前执行的方法传入的参数为指定类型的执行方法；
- @within：用于匹配所以持有指定注解类型内的方法；
- @target：用于匹配当前目标对象类型的执行方法，其中目标对象持有指定的注解；
- @args：用于匹配当前执行的方法传入的参数持有指定注解的执行；
- @annotation：用于匹配当前执行方法持有指定注解的方法；




  AOP：【动态代理】
  		指在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式；

  1、导入aop模块；Spring AOP：(spring-aspects)
  2、定义一个业务逻辑类（AopConfig）；在业务逻辑运行的时候将日志进行打印（方法之前、方法运行结束、方法出现异常，xxx）
  3、定义一个日志切面类（LogAspects）：切面类里面的方法需要动态感知AopConfig.testAop运行到哪里然后执行；
  		通知方法：
  			前置通知(@Before)：logStart：在目标方法(testAop)运行之前运行
  			后置通知(@After)：logEnd：在目标方法(testAop)运行结束之后运行（无论方法正常结束还是异常结束）
  			返回通知(@AfterReturning)：logReturn：在目标方法(testAop)正常返回之后运行
  			异常通知(@AfterThrowing)：logException：在目标方法(testAop)出现异常以后运行
  			环绕通知(@Around)：动态代理，手动推进目标方法运行（joinPoint.proceed()）
  4、给切面类的目标方法标注何时何地运行（通知注解）；
  5、将切面类和业务逻辑类（目标方法所在类）都加入到容器中;
  6、必须告诉Spring哪个类是切面类(给切面类上加一个注解：@Aspect)
  7、给配置类中加 @EnableAspectJAutoProxy 【开启基于注解的aop模式】
  		在Spring中很多的 @EnableXXX;

  三步：
  	1）、将业务逻辑组件和切面类都加入到容器中；告诉Spring哪个是切面类（@Aspect）
  	2）、在切面类上的每一个通知方法上标注通知注解，告诉Spring何时何地运行（切入点表达式）
    3）、开启基于注解的aop模式；@EnableAspectJAutoProxy

  AOP原理：【看给容器中注册了什么组件，这个组件什么时候工作，这个组件的功能是什么？】
  		@EnableAspectJAutoProxy；
  1、@EnableAspectJAutoProxy是什么？
  		@Import(AspectJAutoProxyRegistrar.class)：给容器中导入AspectJAutoProxyRegistrar
  			利用AspectJAutoProxyRegistrar自定义给容器中注册bean；BeanDefinition
  			internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
  		给容器中注册一个AnnotationAwareAspectJAutoProxyCreator；

  2、 AnnotationAwareAspectJAutoProxyCreator：
  		AnnotationAwareAspectJAutoProxyCreator
  			->AspectJAwareAdvisorAutoProxyCreator
  				->AbstractAdvisorAutoProxyCreator
  					->AbstractAutoProxyCreator
  							implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
  						关注后置处理器（在bean初始化完成前后做事情）、自动装配BeanFactory

  AbstractAutoProxyCreator.setBeanFactory()
  AbstractAutoProxyCreator.有后置处理器的逻辑；

  AbstractAdvisorAutoProxyCreator.setBeanFactory()-》initBeanFactory()

  AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()


  流程：
  		1）、传入配置类，创建ioc容器
  		2）、注册配置类，调用refresh（）刷新容器；
  		3）、registerBeanPostProcessors(beanFactory);注册bean的后置处理器来方便拦截bean的创建；
  			1）、先获取ioc容器已经定义了的需要创建对象的所有BeanPostProcessor
  			2）、给容器中加别的BeanPostProcessor
  			3）、优先注册实现了PriorityOrdered接口的BeanPostProcessor；
  			4）、再给容器中注册实现了Ordered接口的BeanPostProcessor；
  			5）、注册没实现优先级接口的BeanPostProcessor；
  			6）、注册BeanPostProcessor，实际上就是创建BeanPostProcessor对象，保存在容器中；
  				创建internalAutoProxyCreator的BeanPostProcessor【AnnotationAwareAspectJAutoProxyCreator】
  				1）、创建Bean的实例
  				2）、populateBean；给bean的各种属性赋值
  				3）、initializeBean：初始化bean；
  						1）、invokeAwareMethods()：处理Aware接口的方法回调
  						2）、applyBeanPostProcessorsBeforeInitialization()：应用后置处理器的postProcessBeforeInitialization（）
  						3）、invokeInitMethods()；执行自定义的初始化方法
  						4）、applyBeanPostProcessorsAfterInitialization()；执行后置处理器的postProcessAfterInitialization（）；
  				4）、BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功；--》aspectJAdvisorsBuilder
  			7）、把BeanPostProcessor注册到BeanFactory中；
  				beanFactory.addBeanPostProcessor(postProcessor);
  =======以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程========
  			AnnotationAwareAspectJAutoProxyCreator => InstantiationAwareBeanPostProcessor
  		4）、finishBeanFactoryInitialization(beanFactory);完成BeanFactory初始化工作；创建剩下的单实例bean
  			1）、遍历获取容器中所有的Bean，依次创建对象getBean(beanName);
  				getBean->doGetBean()->getSingleton()->
  			2）、创建bean
  				【AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前会有一个拦截，InstantiationAwareBeanPostProcessor，会调用postProcessBeforeInstantiation()】
  				1）、先从缓存中获取当前bean，如果能获取到，说明bean是之前被创建过的，直接使用，否则再创建；
  					只要创建好的Bean都会被缓存起来
  				2）、createBean（）;创建bean；
  					AnnotationAwareAspectJAutoProxyCreator 会在任何bean创建之前先尝试返回bean的实例
  					【BeanPostProcessor是在Bean对象创建完成初始化前后调用的】
  					【InstantiationAwareBeanPostProcessor是在创建Bean实例之前先尝试用后置处理器返回对象的】
  					1）、resolveBeforeInstantiation(beanName, mbdToUse);解析BeforeInstantiation
  						希望后置处理器在此能返回一个代理对象；如果能返回代理对象就使用，如果不能就继续
  						1）、后置处理器先尝试返回对象；
  							bean = applyBeanPostProcessorsBeforeInstantiation（）：
  								拿到所有后置处理器，如果是InstantiationAwareBeanPostProcessor;
  								就执行postProcessBeforeInstantiation
  							if (bean != null) {
                               bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
                           }
  					2）、doCreateBean(beanName, mbdToUse, args);真正的去创建一个bean实例；和3.6流程一样；
  					3）、


  AnnotationAwareAspectJAutoProxyCreator【InstantiationAwareBeanPostProcessor】	的作用：
  1）、每一个bean创建之前，调用postProcessBeforeInstantiation()；
  		关心MathCalculator和LogAspect的创建
  		1）、判断当前bean是否在advisedBeans中（保存了所有需要增强bean）
  		2）、判断当前bean是否是基础类型的Advice、Pointcut、Advisor、AopInfrastructureBean，
  			或者是否是切面（@Aspect）
  		3）、是否需要跳过
  			1）、获取候选的增强器（切面里面的通知方法）【List<Advisor> candidateAdvisors】
  				每一个封装的通知方法的增强器是 InstantiationModelAwarePointcutAdvisor；
  				判断每一个增强器是否是 AspectJPointcutAdvisor 类型的；返回true
  			2）、永远返回false

  2）、创建对象
  postProcessAfterInitialization；
  		return wrapIfNecessary(bean, beanName, cacheKey);//包装如果需要的情况下
  		1）、获取当前bean的所有增强器（通知方法）  Object[]  specificInterceptors
  			1、找到候选的所有的增强器（找哪些通知方法是需要切入当前bean方法的）
  			2、获取到能在bean使用的增强器。
  			3、给增强器排序
  		2）、保存当前bean在advisedBeans中；
  		3）、如果当前bean需要增强，创建当前bean的代理对象；
  			1）、获取所有增强器（通知方法）
  			2）、保存到proxyFactory
  			3）、创建代理对象：Spring自动决定
  				JdkDynamicAopProxy(config);jdk动态代理；
  				ObjenesisCglibAopProxy(config);cglib的动态代理；
  		4）、给容器中返回当前组件使用cglib增强了的代理对象；
  		5）、以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程；

  3）、目标方法执行	；
  		容器中保存了组件的代理对象（cglib增强后的对象），这个对象里面保存了详细信息（比如增强器，目标对象，xxx）；
  		1）、CglibAopProxy.intercept();拦截目标方法的执行
  		2）、根据ProxyFactory对象获取将要执行的目标方法拦截器链；
  			List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
  			1）、List<Object> interceptorList保存所有拦截器 5
  				一个默认的ExposeInvocationInterceptor 和 4个增强器；
  			2）、遍历所有的增强器，将其转为Interceptor；
  				registry.getInterceptors(advisor);
  			3）、将增强器转为List<MethodInterceptor>；
  				如果是MethodInterceptor，直接加入到集合中
  				如果不是，使用AdvisorAdapter将增强器转为MethodInterceptor；
  				转换完成返回MethodInterceptor数组；
  		3）、如果没有拦截器链，直接执行目标方法;
  			拦截器链（每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制）
  		4）、如果有拦截器链，把需要执行的目标对象，目标方法，
  			拦截器链等信息传入创建一个 CglibMethodInvocation 对象，
  			并调用 Object retVal =  mi.proceed();
  		5）、拦截器链的触发过程;
  			1)、如果没有拦截器执行执行目标方法，或者拦截器的索引和拦截器数组-1大小一样（指定到了最后一个拦截器）执行目标方法；
  			2)、链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成返回以后再来执行；
  				拦截器链的机制，保证通知方法与目标方法的执行顺序；

  总结：
  		1）、  @EnableAspectJAutoProxy 开启AOP功能
  		2）、 @EnableAspectJAutoProxy 会给容器中注册一个组件 AnnotationAwareAspectJAutoProxyCreator
  		3）、AnnotationAwareAspectJAutoProxyCreator是一个后置处理器；
  		4）、容器的创建流程：
  			1）、registerBeanPostProcessors（）注册后置处理器；创建AnnotationAwareAspectJAutoProxyCreator对象
  			2）、finishBeanFactoryInitialization（）初始化剩下的单实例bean
  				1）、创建业务逻辑组件和切面组件
  				2）、AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
  				3）、组件创建完之后，判断组件是否需要增强
  					是：切面的通知方法，包装成增强器（Advisor）;给业务逻辑组件创建一个代理对象（cglib）；
  		5）、执行目标方法：
  			1）、代理对象执行目标方法
  			2）、CglibAopProxy.intercept()；
  				1）、得到目标方法的拦截器链（增强器包装成拦截器MethodInterceptor）
  				2）、利用拦截器的链式机制，依次进入每一个拦截器进行执行；
  				3）、效果：
  					正常执行：前置通知-》目标方法-》后置通知-》返回通知
  					出现异常：前置通知-》目标方法-》后置通知-》异常通知


