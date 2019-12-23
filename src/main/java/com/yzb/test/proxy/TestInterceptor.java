package com.yzb.test.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TestInterceptor implements MethodInterceptor {
    private Object target;
    public TestInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        switch (method.getName()) {
            case "test":
                preTest();
                break;
        }
        Object invoke = methodProxy.invoke(target, args);
        return invoke;
    }

    private void preTest() {
        System.out.println("preTest");
    }
}
