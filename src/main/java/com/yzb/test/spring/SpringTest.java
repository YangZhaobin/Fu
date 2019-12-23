package com.yzb.test.spring;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

@ComponentScan
public class SpringTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringTest.class);
//        System.out.println(context.getBean(TestBean.User.class));

//        context.publishEvent(new ApplicationEvent("Event!!") {
//        });
//        context.removeBeanDefinition("user");
        context.close();



//        BeanFactoryPostProcessor postProcessBeanFactory

//  Bean的实例化：
//        InstantiationAwareBeanPostProcessor  postProcessBeforeInstantiation
//        User constructor
//        InstantiationAwareBeanPostProcessor  postProcessAfterInstantiation
//        InstantiationAwareBeanPostProcessor  postProcessProperties
//        R constructor
//        BeanNameAware  setBeanName()  user
//        BeanFactoryAware  setBeanFactory()
//        ApplicationContextAware  setApplicationContext()

//  Bean的初始化：
//        BeanPostProcessor postProcessBeforeInitialization
//        @PostConstruct
//        InitializingBean afterPropertiesSet
//        initMethod
//        BeanPostProcessor postProcessAfterInitialization

//  Bean的销毁：
//      单例Bean在容器关闭或者移除容器的时候会执行销毁操作
//      原型Bean容器不管理其销毁操作
//        @PreDestroy
//        DisposableBean destroy
//        destroyMethod
    }
}
