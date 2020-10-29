package com.test.annotation.processor;

import com.test.annotation.bean.Person;
import com.test.annotation.bean.PersonLifeCycle;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

/**
 * 这个有3个方法，其中第二个方法postProcessAfterInitialization就是重写了BeanPostProcessor的方法。
 * 第三个方法postProcessPropertyValues用来操作属性，返回值也应该是PropertyValues对象。
 */
@Component
public class MyInstantiationAwareBeanPostProcessor  extends
        InstantiationAwareBeanPostProcessorAdapter {

    public MyInstantiationAwareBeanPostProcessor() {
        super();
        System.out.println("这是InstantiationAwareBeanPostProcessorAdapter实现类构造器！！");
    }

    // 接口方法、实例化Bean之前调用
    @Override
    public Object postProcessBeforeInstantiation(Class beanClass,
                                                 String beanName) throws BeansException {
        if (beanClass == PersonLifeCycle.class) {
            System.out.println("【InstantiationAwareBeanPostProcessor】调用postProcessBeforeInstantiation方法");
        }
        return null;
    }

    // 接口方法、实例化Bean之后调用
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof PersonLifeCycle) {
            System.out.println("【InstantiationAwareBeanPostProcessor】调用postProcessAfterInitialization方法");
        }
        return bean;
    }

    // 接口方法、设置某个属性时调用
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs,
                                                    PropertyDescriptor[] pds, Object bean, String beanName)
            throws BeansException {
        if (bean instanceof PersonLifeCycle) {
            System.out.println("【InstantiationAwareBeanPostProcessor】调用postProcessPropertyValues方法");
        }
        return pvs;
    }
}
