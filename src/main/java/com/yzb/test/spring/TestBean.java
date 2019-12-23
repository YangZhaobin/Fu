package com.yzb.test.spring;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.aop.framework.AopProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.boot.web.servlet.context.WebApplicationContextServletContextAwareProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.beans.PropertyDescriptor;
import java.util.Arrays;

@Configuration
public class TestBean  {


    @Component
    public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        /**
         * postProcessBeforeInstantiation方法是最先执行的方法，它在目标对象实例化之前调用，
         * 该方法的返回值类型是Object，我们可以任何类型的值。
         * 由于这个时候目标对象还未实例化，所以这个返回值可以用来代替原本该生成的目标对象的实例(比如代理对象)。
         * 如果该方法的返回值代替原本该生成的目标对象，后续只有postProcessAfterInitialization方法会调用，其它方法不再调用；
         * 否则按照正常的流程走
         */
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if ("user".equals(beanName)) {
                System.out.println("InstantiationAwareBeanPostProcessor  postProcessBeforeInstantiation");
//                return new User().setName("haha");
            }
            return null;
        }

        /**
         * @return {@code true} if properties should be set on the bean;
         *          {@code false} if property population should be skipped.
         *          Normal implementations should return {@code true}.
         */
        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if ("user".equals(beanName))
                System.out.println("InstantiationAwareBeanPostProcessor  postProcessAfterInstantiation");
            return true;
        }

        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            if ("user".equals(beanName))
                System.out.println("InstantiationAwareBeanPostProcessor  postProcessProperties");
            return pvs;
        }

        @Override
        public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
            if ("user".equals(beanName)) {
                System.out.println("InstantiationAwareBeanPostProcessor  postProcessPropertyValues");
            }
            return pvs;
        }
    }

    /**
     * 如果某个IoC容器中增加的实现BeanPostProcessor接口的实现类Bean
     * 那么在该容器中实例化Bean之后:
     * 初始化前执行该接口中的postProcessBeforeInitialization()方法进行初始化预处理
     * 初始化后执行该接口中的postProcessorAfterInitialization()方法进行初始化后处理
     */
    @Component
    public class MyBeanPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof User) {
                System.out.println("\nBeanPostProcessor postProcessBeforeInitialization");
            }
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof User) {
                System.out.println("BeanPostProcessor postProcessAfterInitialization");
            }
            return bean;
        }
    }

    /**
     * InitializingBean：如果Bean实现了InitializingBean接口，那么Bean在实例化完成后将会执行接口中的afterPropertiesSet()方法来进行初始化
     *
     * DisposableBean：如果Bean实现了DisposableBean接口，Spring将会在Bean实例销毁之前调用该接口的destory()方法，来完成一些销毁之前的处理工作。
     */
    @Data
    @Accessors(chain = true)
    public class User implements BeanNameAware, BeanFactoryAware,
            ApplicationContextAware, InitializingBean, DisposableBean, SmartInitializingSingleton  {
        @Autowired
        R r;
        @Value("yang")
        String name;

        User() {
            System.out.println("User constructor");
        }

        public void initMethod() {
            System.out.println("initMethod");
        }

        public void destroyMethod() {
            System.out.println("destroyMethod");
        }

        @PostConstruct
        public void postConstruct() {
            System.out.println("@PostConstruct");
        }

        @PreDestroy
        public void preDestroy() {
            System.out.println("\n@PreDestroy");
        }

        @Override
        public void afterPropertiesSet() {
            System.out.println("InitializingBean afterPropertiesSet");
        }

        @Override
        public void destroy() {
            System.out.println("DisposableBean destroy");
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            System.out.println("BeanFactoryAware  setBeanFactory()");
        }

        @Override
        public void setBeanName(String name) {
            System.out.println("BeanNameAware  setBeanName()  " + name);
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            System.out.println("ApplicationContextAware  setApplicationContext()");
        }

        @Override
        public void afterSingletonsInstantiated() {
            System.out.println("User SmartInitializingSingleton afterSingletonsInstantiated.");
        }
    }

    /**
     * 所有bean创建完成之后，检查所有bean是否实现SmartInitializingSingleton
     * 如果是，则执行SmartInitializingSingleton.afterSingletonsInstantiated方法。
     */
    public class R implements SmartInitializingSingleton {
        R() {
            System.out.println("R constructor");
        }

        @Override
        public void afterSingletonsInstantiated() {
            System.out.println("R SmartInitializingSingleton afterSingletonsInstantiated.");
        }
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public User user() {
        return new User();
    }

    @Bean
    public R r() {
        return new R();
    }
}
