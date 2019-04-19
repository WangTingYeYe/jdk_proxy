package com.wanting.handle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class logHandle implements InvocationHandler {

    private Object target;

    public logHandle(Object target) {
        this.target = target;
    }

    /**
     *
     * @param proxy 当前代理对象
     * @param method 当前代理的目标方法
     * @param args 当前代理的目标方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("log");
        return  method.invoke(target);
    }
}
