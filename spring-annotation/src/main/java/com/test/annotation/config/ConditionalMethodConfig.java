package com.test.annotation.config;

import com.test.annotation.bean.Person;
import com.test.annotation.condition.LinuxCondition;
import com.test.annotation.condition.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalMethodConfig {
    /**
     * @Conditional:是SpringBoot底层大量使用的注解，按照一定的条件来进行判断，满足条件给容器注册bean
     */

    /**
     *  现在下面的两个bean注册到IOC容器是必要条件的：
     *  1.如果系统是windows，给容器注册("bill")
     *  1.如果系统是linux，给容器注册("linus")
     * @return
     */
    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person person01(){
        return new Person("bill","美国",50);
    }

    @Conditional({LinuxCondition.class})
    @Bean("linus")
    public Person person02() {
        return new Person("linus","美国",40);
    }
}
