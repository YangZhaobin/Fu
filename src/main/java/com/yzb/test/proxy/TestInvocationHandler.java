package com.yzb.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK 动态代理
 */
public class TestInvocationHandler implements InvocationHandler {

    private Object target;

    public TestInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        switch (method.getName()) {
            case "test":
                preTest();
                break;
        }
        Object invoke = method.invoke(target, args);
        return invoke;
    }

    private void preTest() {
        System.out.println("preTest");
    }
}
