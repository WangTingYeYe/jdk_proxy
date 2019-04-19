package com.wanting;

import com.wanting.handle.logHandle;
import com.wanting.service.TestService;
import com.wanting.service.impl.TestServiceImpl;
import com.wanting.strengthen_service.StrengthenTestService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理测试
 */
public class App {
    public static void main(String[] args)
    {
        //这是我们的目标对象
        TestService target =  new TestServiceImpl();

        //用 "代理"对象来增强其功能
//        TestService testService = new StrengthenTestService(target);
//        testService.selectData();


//        TestService testService = (TestService) MyJDKProxyUtil.newInstance(target);
//
//        testService.selectData();

        TestService testService = (TestService) Proxy.newProxyInstance(App.class.getClassLoader(),
                new Class[]{TestService.class},
                new logHandle(target));

        testService.selectData();
    }
}
