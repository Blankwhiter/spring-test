package com.test.annotation.factorybean;

import com.test.annotation.bean.Person;
import org.springframework.beans.factory.FactoryBean;

/**
 * 创建一个Spring定义的FactoryBean
 */
public class PersonFactoryBean implements FactoryBean<Person> {
    //返回一个Color对象，这个对象会添加到容器中
    @Override
    public Person getObject() throws Exception {
        return new Person();
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }

    //控制是否为单例
    // true：表示的就是一个单实例，在容器中保存一份
    // false:多实例，每次获取都会创建一个新的bean
    @Override
    public boolean isSingleton() {
        return true;
    }
}
