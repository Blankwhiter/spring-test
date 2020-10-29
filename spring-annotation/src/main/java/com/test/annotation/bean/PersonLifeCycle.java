package com.test.annotation.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *
 * bean的生命周期：bean的创建->初始化->销毁的过程
 * 容器管理bean的生命周期：
 * 我们可以自定义初始化方法和销毁的方法：容器在bean进行到当前的生命周期的时候，来调用我们自定义的初始化方法和销毁方法
 * 构造（对象创建）：
 *      单实例：在容器启动的时候创建对象
 *      多实例：在每次获取的时候来创建对象
 * 初始化方法：
 *      对象创建完成，并赋值好，调用初始化方法
 * 销毁方法：
 *     单实例的bean:在容器关闭的时候进行销毁
 *      多实例的bean:容器不会管理这个bean,容器不会调用销毁的方法
 *
 *    1)指定初始化方法和销毁方法；
 *        -我们可以通过@Bean(initMethod = "init",destroyMethod = "destroy")来指定初始化方法和销毁方法
 *        相当于xml配置文件bean标签里面的 init-method="" 和 destroy-method="" 属性
 *    2)通过bean实现InitializingBean（定义初始化逻辑）
 *               DisposableBean（定义销毁逻辑）；
 *    3）可以使用JSR250规范里面定义的两个注解：
 *      @PostConstruct :在bean创建完成并且属性赋值完成，来执行初始化方法
 *      @PreDestroy ：在容器销毁bean之前通知我们来进行清理工作
 *   4）BeanPostProcessor接口：bean的后置处理器
 *      在bean初始化前后做一些处理工作，这个接口有两个方法：
 *        postProcessBeforeInitialization：在初始化之前(InitializingBean's afterPropertiesSet|init-method之前)工作
 *        postProcessAfterInitialization：在初始化之后(InitializingBean's afterPropertiesSet|init-method之后)工作
 *
 * Bean的完整生命周期经历了各种方法调用，这些方法可以划分为以下几类：
 *
 * 1、Bean自身的方法　　：　　这个包括了Bean本身调用的方法和通过配置文件中<bean>的init-method(初始化)和destroy-method(销毁)指定的方法
 *
 * 2、Bean级生命周期接口方法　　：　　这个包括了BeanNameAware、BeanFactoryAware、InitializingBean和DisposableBean这些接口的方法
 *
 * 3、容器级生命周期接口方法　　：　　这个包括了InstantiationAwareBeanPostProcessor 和 BeanPostProcessor 这两个接口实现，一般称它们的实现类为“后处理器”。
 *
 * 4、工厂后处理器接口方法　　：　　这个包括了AspectJWeavingEnabler, ConfigurationClassPostProcessor,
 *                                CustomAutowireConfigurer等等非常有用的工厂后处理器 接口的方法。工厂后处理器也是容器级的。
 *                                在应用上下文装配配置文件之后立即调用。
 *
 *
 */
public class PersonLifeCycle implements BeanNameAware, BeanFactoryAware,
        InitializingBean, DisposableBean {
    private String name;
    @Value("${personLifeCycle.address}")
    private String address;
    private int age;


    private BeanFactory beanFactory;
    private String beanName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //给beans.xml使用的默认无参构造方法
    public PersonLifeCycle() {
        System.out.println("[2]【构造器】调用PersonLifeCycle的无参构造器实例化");
    }

    public PersonLifeCycle(String name, String address, int age) {
        System.out.println("[2]【构造器】调用PersonLifeCycle的有参构造器实例化");
        this.name = name;
        this.address = address;
        this.age = age;
    }



    // 这是BeanNameAware接口方法
    @Override
    public void setBeanName(String arg0) {
        System.out.println("[3]【BeanNameAware接口】调用BeanNameAware.setBeanName()");
        this.beanName = arg0;
    }

    // 这是BeanFactoryAware接口方法
    @Override
    public void setBeanFactory(BeanFactory arg0) throws BeansException {
        System.out.println("[4]【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()");
        this.beanFactory = arg0;
    }

    //在bean创建完成并且属性赋值完成，来执行初始化方法
    @PostConstruct
    public void postConstruct() {
        System.out.println("[6] 调用<bean>的@PostConstruct");
    }


    // 这是InitializingBean接口方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[7]【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }
    // 通过<bean>的init-method属性指定的初始化方法
    public void myInit() {
        System.out.println("[8]【init-method】调用<bean>的init-method属性指定的初始化方法");
    }

    //在容器销毁bean之前通知我们来进行清理工作
    @PreDestroy
    public void preDestroy() {
        System.out.println("[15]调用<bean>的PreDestroy()");
    }

    // 这是DiposibleBean接口方法
    @Override
    public void destroy() throws Exception {
        System.out.println("[16]【DisposableBean接口】调用DisposableBeanBean.destroy()");
    }

    // 通过<bean>的destroy-method属性指定的初始化方法
    public void myDestroy() {
        System.out.println("[17]【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }

}
