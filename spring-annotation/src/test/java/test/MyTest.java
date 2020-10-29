package test;

import com.test.annotation.bean.Computer;
import com.test.annotation.bean.Person;
import com.test.annotation.bean.PersonLifeCycle;
import com.test.annotation.config.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Map;

public class MyTest {


    @Test
    public void TestBeanXml() {
//        ClassPathResource resource = new ClassPathResource("beans.xml");
//        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
//        reader.loadBeanDefinitions(resource);
//
//        Arrays.asList(factory.getBeanNamesForType(Person.class)).forEach(name->{
//            System.out.println(name +  ": " + factory.getBean(name));
//        });
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
    }

    @Test
    public void TestAnnotationConfig() {
        TestConfig(AnnotationConfig.class);
    //  result:
    //        org.springframework.context.annotation.internalConfigurationAnnotationProcessor: org.springframework.context.annotation.ConfigurationClassPostProcessor@12d4bf7e
    //        org.springframework.context.annotation.internalAutowiredAnnotationProcessor: org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@4c1d9d4b
    //        org.springframework.context.annotation.internalRequiredAnnotationProcessor: org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor@7b227d8d
    //        org.springframework.context.annotation.internalCommonAnnotationProcessor: org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@7219ec67
    //        org.springframework.context.event.internalEventListenerProcessor: org.springframework.context.event.EventListenerMethodProcessor@45018215
    //        org.springframework.context.event.internalEventListenerFactory: org.springframework.context.event.DefaultEventListenerFactory@65d6b83b
    //        annotationConfig: com.test.annotation.config.AnnotationConfig$$EnhancerBySpringCGLIB$$29163320@d706f19
    //        person: Person{name='person', address='北京', age=19}
    //        person01: Person{name='person01', address='上海', age=20}
    //        customerPerson: Person{name='customerPerson', address='广州', age=21}
    //        personScope: Person{name='personScope', address='北京', age=19}
    }

    @Test
    public void TestAnnotationLazyConfig() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationLazyConfig.class);
        System.out.println("获取单例");
        Object person1 = context.getBean("person");
        System.out.println("获取单例懒加载");
        Object person2= context.getBean("personLazy");
        context.close();
        //  result:
        //        person创建开始
        //                获取单例
        //        获取单例懒加载
        //                personLazy创建开始
    }

    @Test
    public void TestBeanScope() {
        //这里是new了一个AnnotationConfigApplicationContext对象，以前new的ClassPathXmlApplicationContext对象
        //的构造函数里面传的是配置文件的位置，而现在AnnotationConfigApplicationContext对象的构造函数里面传的是配置类的类型
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        Object person1 = context.getBean("person");
        Object person2= context.getBean("person");
        System.out.println("person1==person2: "+ (person1 == person2));
        Object personScope1 = context.getBean("personScope");
        Object personScope2 = context.getBean("personScope");
        System.out.println("personScope1==personScope2: "+ (personScope1 == personScope2));
        context.close();
    //  result:
    //       person1==person2: true
    //       personScope1==personScope2: false
        // 还有一点：当作用域为单例的时候，IOC容器在启动的时候，就会将容器中所有作用域为单例的bean的实例给创建出来；以后的每次获取，就直接从IOC容器中来获取
        // 当bean是作用域为多例的时候，IOC容器启动的时候，就不会去创建bean的实例的，而是当我们调用getBean()获取的时候去创建bean的实例；而且每次调用的时候，都会创建bean的实例；
    }

    @Test
    public void TestComponentScanExcludeConfig() {
        TestConfig(ComponentScanExcludeConfig.class);
    //  result:
    //        org.springframework.context.annotation.internalConfigurationAnnotationProcessor: org.springframework.context.annotation.ConfigurationClassPostProcessor@3aefe5e5
    //        org.springframework.context.annotation.internalAutowiredAnnotationProcessor: org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@149e0f5d
    //        org.springframework.context.annotation.internalRequiredAnnotationProcessor: org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor@1b1473ab
    //        org.springframework.context.annotation.internalCommonAnnotationProcessor: org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@2f7c2f4f
    //        org.springframework.context.event.internalEventListenerProcessor: org.springframework.context.event.EventListenerMethodProcessor@6af93788
    //        org.springframework.context.event.internalEventListenerFactory: org.springframework.context.event.DefaultEventListenerFactory@ef9296d
    //        componentScanExcludeConfig: com.test.annotation.config.ComponentScanExcludeConfig$$EnhancerBySpringCGLIB$$640d4688@36c88a32
    //        annotationConfig: com.test.annotation.config.AnnotationConfig$$EnhancerBySpringCGLIB$$15390f7@7880cdf3
    //        componentScanIncludeConfig: com.test.annotation.config.ComponentScanIncludeConfig$$EnhancerBySpringCGLIB$$4171e96@5be6e01c
    //        baseDao: com.test.annotation.dao.BaseDao@1c93084c
    //        baseController: com.test.annotation.controller.BaseController@6ef888f6
    //        person: Person{name='person', address='北京', age=19}
    //        person01: Person{name='person01', address='上海', age=20}
    //        customerPerson: Person{name='customerPerson', address='广州', age=21}
    }

    @Test
    public void TestComponentScanIncludeConfig() {
        TestConfig(ComponentScanIncludeConfig.class);
    //   result:
    //        org.springframework.context.annotation.internalConfigurationAnnotationProcessor: org.springframework.context.annotation.ConfigurationClassPostProcessor@25d250c6
    //        org.springframework.context.annotation.internalAutowiredAnnotationProcessor: org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@4df50bcc
    //        org.springframework.context.annotation.internalRequiredAnnotationProcessor: org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor@6b26e945
    //        org.springframework.context.annotation.internalCommonAnnotationProcessor: org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@63a65a25
    //        org.springframework.context.event.internalEventListenerProcessor: org.springframework.context.event.EventListenerMethodProcessor@54c562f7
    //        org.springframework.context.event.internalEventListenerFactory: org.springframework.context.event.DefaultEventListenerFactory@318ba8c8
    //        componentScanIncludeConfig: com.test.annotation.config.ComponentScanIncludeConfig$$EnhancerBySpringCGLIB$$f4f04a21@6dbb137d
    //        baseController: com.test.annotation.controller.BaseController@3c9d0b9d
    //        baseDao: com.test.annotation.dao.BaseDao@43301423
    }

    @Test
    public void TestComponentScansConfig() {
        TestConfig(ComponentScansConfig.class);
        //   result:
        //        org.springframework.context.annotation.internalConfigurationAnnotationProcessor: org.springframework.context.annotation.ConfigurationClassPostProcessor@5223e5ee
        //        org.springframework.context.annotation.internalAutowiredAnnotationProcessor: org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@bef2d72
        //        org.springframework.context.annotation.internalRequiredAnnotationProcessor: org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor@69b2283a
        //        org.springframework.context.annotation.internalCommonAnnotationProcessor: org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@22a637e7
        //        org.springframework.context.event.internalEventListenerProcessor: org.springframework.context.event.EventListenerMethodProcessor@6fe7aac8
        //        org.springframework.context.event.internalEventListenerFactory: org.springframework.context.event.DefaultEventListenerFactory@1d119efb
        //        componentScansConfig: com.test.annotation.config.ComponentScansConfig$$EnhancerBySpringCGLIB$$ded743bc@659a969b
        //        annotationConfig: com.test.annotation.config.AnnotationConfig$$EnhancerBySpringCGLIB$$e46cf692@76908cc0
        //        componentScanExcludeConfig: com.test.annotation.config.ComponentScanExcludeConfig$$EnhancerBySpringCGLIB$$4726ac23@2473d930
        //        componentScanIncludeConfig: com.test.annotation.config.ComponentScanIncludeConfig$$EnhancerBySpringCGLIB$$e7308431@35047d03
        //        baseController: com.test.annotation.controller.BaseController@49b0b76
        //        baseDao: com.test.annotation.dao.BaseDao@769f71a9
        //        person: Person{name='person', address='北京', age=19}
        //        person01: Person{name='person01', address='上海', age=20}
        //        customerPerson: Person{name='customerPerson', address='广州', age=21}
    }


    @Test
    public void TestComponentScanCustomExcludeConfig() {
        TestConfig(ComponentScanCustomIncludeConfig.class);
        //   result:
        //        符合条件的是 com.test.annotation.dao.BaseDao
        //        org.springframework.context.annotation.internalConfigurationAnnotationProcessor: org.springframework.context.annotation.ConfigurationClassPostProcessor@18ce0030
        //        org.springframework.context.annotation.internalAutowiredAnnotationProcessor: org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@4445629
        //        org.springframework.context.annotation.internalRequiredAnnotationProcessor: org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor@45b9a632
        //        org.springframework.context.annotation.internalCommonAnnotationProcessor: org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@25d250c6
        //        org.springframework.context.event.internalEventListenerProcessor: org.springframework.context.event.EventListenerMethodProcessor@4df50bcc
        //        org.springframework.context.event.internalEventListenerFactory: org.springframework.context.event.DefaultEventListenerFactory@6b26e945
        //        componentScanCustomIncludeConfig: com.test.annotation.config.ComponentScanCustomIncludeConfig$$EnhancerBySpringCGLIB$$ec927ca7@63a65a25
        //        baseDao: com.test.annotation.dao.BaseDao@54c562f7
    }


    @Test
    public void TestConditionalMethodConfig(){
        //运行时设置vm options为-Dos.name=linux
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionalMethodConfig.class);

        //我们可以获取当前的操作系统是什么：
        Environment environment = applicationContext.getEnvironment();
        //动态的获取环境变量的值：Windows 7
        String property = environment.getProperty("os.name");
        System.out.println(property);

        //获取IOC容器类型为Person类型的bean的名字一共有哪些
        String[] definitionNames = applicationContext.getBeanNamesForType(Person.class);
        for (String name : definitionNames) {
            System.out.println(name);
        }
        //我们也可以来获取类型为Person类型的对象
        Map<String, Person> persons = applicationContext.getBeansOfType(Person.class);
        persons.values().forEach(System.out::println);

    //  result:
    //        linux
    //                linus
    //        Person{name='linus', address='美国', age=40}
    }


    @Test
    public void TestConditionalClassConfig(){
        //运行时设置vm options为-Dimport=false
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionalClassConfig.class);
        Environment environment = applicationContext.getEnvironment();

        String property = environment.getProperty("import");
        System.out.println(property);

        //获取IOC容器类型为Person类型的bean的名字一共有哪些
        String[] definitionNames = applicationContext.getBeanNamesForType(Person.class);
        for (String name : definitionNames) {
            System.out.println(name);
        }
        //我们也可以来获取类型为Person类型的对象
        Map<String, Person> persons = applicationContext.getBeansOfType(Person.class);
        persons.values().forEach(System.out::println);

        //当传-Dimport=true 会将两个bean打印出来
        //  result:
        //        false
    }


    @Test
    public void TestImportConfig() {
        TestConfig(ImportConfig.class);
        //  result:
        //        org.springframework.context.annotation.internalConfigurationAnnotationProcessor: org.springframework.context.annotation.ConfigurationClassPostProcessor@163e4e87
        //        org.springframework.context.annotation.internalAutowiredAnnotationProcessor: org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@56de5251
        //        org.springframework.context.annotation.internalRequiredAnnotationProcessor: org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor@419c5f1a
        //        org.springframework.context.annotation.internalCommonAnnotationProcessor: org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@12b0404f
        //        org.springframework.context.event.internalEventListenerProcessor: org.springframework.context.event.EventListenerMethodProcessor@769e7ee8
        //        org.springframework.context.event.internalEventListenerFactory: org.springframework.context.event.DefaultEventListenerFactory@5276e6b0
        //        importConfig: com.test.annotation.config.ImportConfig$$EnhancerBySpringCGLIB$$5dd59148@71b1176b
        //        com.test.annotation.bean.Head: com.test.annotation.bean.Head@6193932a
        //        com.test.annotation.bean.Heart: com.test.annotation.bean.Heart@647fd8ce
        //        com.test.annotation.bean.Hands: com.test.annotation.bean.Hands@159f197
        //        com.test.annotation.bean.Legs: com.test.annotation.bean.Legs@78aab498
        //        person: Person{name='person', address='北京', age=19}
        //        brain: com.test.annotation.bean.Brain@5dd6264
        //        eyes: com.test.annotation.bean.Eyes@1ffe63b9
        //        nose: com.test.annotation.bean.Nose@51e5fc98
    }

    @Test
    public void TestFactoryBeanConfig() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
        Arrays.asList(context.getBeanDefinitionNames()).forEach(name->{
            System.out.println(name );
        });
        //工厂bean获取的是调用getObject方法创建的对象
        Object personFactoryBean = context.getBean("personFactoryBean");
        System.out.println("bean的类型："+personFactoryBean.getClass());

        //如果我们就想要获取这个工厂bean，我们就可以在id的前面加上一个"&"符号
        Object personFactoryBean2 = context.getBean("&personFactoryBean");
        System.out.println("factoryBean的类型："+personFactoryBean2.getClass());
        //  result:
        //        org.springframework.context.annotation.internalConfigurationAnnotationProcessor
        //        org.springframework.context.annotation.internalAutowiredAnnotationProcessor
        //        org.springframework.context.annotation.internalRequiredAnnotationProcessor
        //        org.springframework.context.annotation.internalCommonAnnotationProcessor
        //        org.springframework.context.event.internalEventListenerProcessor
        //        org.springframework.context.event.internalEventListenerFactory
        //                factoryBeanConfig
        //        personFactoryBean
        //        bean的类型：class com.test.annotation.bean.Person
        //        factoryBean的类型：class com.test.annotation.factorybean.PersonFactoryBean
    }



    @Test
    public void TestSingletonLifeCycleConfig() {
        System.out.println(" ====== 开始=====");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        System.out.println("[10] ======容器初始化结束=====");
        System.out.println("[11] ======单例获取开始======");
        PersonLifeCycle bean1 = (PersonLifeCycle) context.getBean("personLifeCycle");
        System.out.println("[12] "+bean1);
        System.out.println("[13] ======单例获取结束======");
        System.out.println("[14] ======容器关闭开始=====");
        context.close();
        System.out.println("[18] ======容器关闭结束=====");
    //   result:
    //                ====== 开始=====
    //                这是BeanPostProcessor实现类构造器！！
                //   上面是spring自带的组件
    //                [1] personLifeCycle bean 初始化
    //                [2]【构造器】调用Person的构造器实例化
    //                [3]【BeanNameAware接口】调用BeanNameAware.setBeanName()
    //                [4]【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()
    //                [5] 【BeanPostProcessor接口】 BeanPostProcessor.postProcessBeforeInitialization对属性进行更改！
    //                [6] 调用<bean>的PostConstruct
    //                [7]【InitializingBean接口】调用InitializingBean.afterPropertiesSet()
    //                [8]【init-method】调用<bean>的init-method属性指定的初始化方法
    //                [9] 【BeanPostProcessor接口】调用BeanPostProcessor.postProcessAfterInitialization对属性进行更改！
    //                [10] ======容器初始化结束=====
    //                [11] ======单例获取开始======
    //                [12] Person{name='belonghuang', address='福州', age=21}
    //                [13] ======单例获取结束======
    //                [14] ======容器关闭开始=====
    //                [15]调用<bean>的PreDestroy()
    //                [16]【DisposableBean接口】调用DisposableBeanBean.destory()
    //                [17]【destroy-method】调用<bean>的destroy-method属性指定的初始化方法
    //                [18] ======容器关闭结束=====
    }
    @Test
    public void TestPrototypeLifeCycleConfig() {

        //为了不影响结果 先讲LifeCycleConfig单例的bean注释掉（即LifeCycleConfig.java第14行至18行代码注释）
        System.out.println(" ====== 开始=====");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        System.out.println("[10] ======容器初始化结束=====");
        System.out.println("[11] =====多例获取开始======");
        PersonLifeCycle bean2 = (PersonLifeCycle) context.getBean("personLifeCycleScope");
        System.out.println(bean2);
        System.out.println("[12] =====多例获取结束======");
        System.out.println("[13] ======单例获取结束======");
        System.out.println("[14] ======容器关闭开始=====");
        context.close();
        System.out.println("[18] ======容器关闭结束=====");
        //可以看到多例的对象销毁 并不由spring容器处理
    //     result:
    //         ====== 开始=====
    //        这是BeanPostProcessor实现类构造器！！

    //        [10] ======容器初始化结束=====
    //        [11] =====多例获取开始======
    //        [1] personLifeCycleScope 初始化
    //        [2]【构造器】调用Person的构造器实例化
    //        [3]【BeanNameAware接口】调用BeanNameAware.setBeanName()
    //        [4]【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()
    //        [5] 【BeanPostProcessor接口】 BeanPostProcessor.postProcessBeforeInitialization对属性进行更改！
    //        [6] 调用<bean>的PostConstruct
    //        [7]【InitializingBean接口】调用InitializingBean.afterPropertiesSet()
    //        [8]【init-method】调用<bean>的init-method属性指定的初始化方法
    //        [9] 【BeanPostProcessor接口】调用BeanPostProcessor.postProcessAfterInitialization对属性进行更改！
    //        Person{name='belonghuang', address='福州', age=20}
    //        [12] =====多例获取结束======
    //        [13] ======单例获取结束======
    //        [14] ======容器关闭开始=====
    //        [18] ======容器关闭结束=====

    }

    @Test
    public void TestValuePropertySourceConfig(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ValuePropertySourceConfig.class);
        Computer computer = context.getBean(Computer.class);
        System.out.println(computer);
        //        result:
        //           Computer{brand='ThinkPad', size='15', cpu='i7', memory='32G'}
    }

    public void TestConfig(Class config) {
        //这里是new了一个AnnotationConfigApplicationContext对象，以前new的ClassPathXmlApplicationContext对象
        //的构造函数里面传的是配置文件的位置，而现在AnnotationConfigApplicationContext对象的构造函数里面传的是配置类的类型
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(config);
        Arrays.asList(context.getBeanDefinitionNames()).forEach(name->{
            System.out.println(name +  ": " + context.getBean(name));
        });
        context.close();
    }
}
