package com.yzb.test.proxy;

import net.sf.cglib.proxy.Enhancer;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.AutoProxyUtils;
import org.springframework.context.annotation.AutoProxyRegistrar;

import java.lang.reflect.Proxy;

public class ProxyTestImpl implements ProxyTest {

    @Override
    public void test() {
        System.out.println("test");
    }

    public static void main(String[] args) {
        // JDK 动态代理
        ProxyTest target = new ProxyTestImpl();

        ProxyTest proxyTest = (ProxyTest) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),               // classloader 生成代理类
                target.getClass().getInterfaces(),                       // 代理类应该实现的接口
                new TestInvocationHandler(target));    // 实现InvocationHandler的切面类
        proxyTest.test();

        // CGLIB 动态代理
        ProxyTest cglibProxy = (ProxyTest) Enhancer.create(target.getClass(), new TestInterceptor(target));
        cglibProxy.test();
    }
}
