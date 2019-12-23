package com.yzb.test.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 某个IoC容器内添加了实现了BeanFactoryPostProcessor接口的实现类Bean，
 * 那么在该容器中实例化任何其他Bean之前可以回调该Bean中的postProcessBeanFactory()方法来对Bean的配置元数据进行更改，
 * 比如从XML配置文件中获取到的配置信息。
 */

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    public MyBeanFactoryPostProcessor() {
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor postProcessBeanFactory");
    }

}