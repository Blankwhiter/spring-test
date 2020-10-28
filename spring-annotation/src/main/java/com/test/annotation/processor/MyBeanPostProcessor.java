package com.test.annotation.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * BeanPostProcessor接口包括2个方法postProcessAfterInitialization和postProcessBeforeInitialization，
 * 这两个方法的第一个参数都是要处理的Bean对象，第二个参数都是Bean的name。返回值也都是要处理的Bean对象。
 */
@Component
public class MyBeanPostProcessor  implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        super();
        System.out.println("这是BeanPostProcessor实现类构造器！！");
        // TODO Auto-generated constructor stub
    }
    @Override
    public Object postProcessBeforeInitialization(Object arg0, String arg1)
            throws BeansException {
        System.out.println("[5] 【BeanPostProcessor接口】 BeanPostProcessor.postProcessBeforeInitialization对属性进行更改！");
        return arg0;
    }
    @Override
    public Object postProcessAfterInitialization(Object arg0, String arg1)
            throws BeansException {
        System.out.println("[9] 【BeanPostProcessor接口】调用BeanPostProcessor.postProcessAfterInitialization对属性进行更改！");
        return arg0;
    }

}
