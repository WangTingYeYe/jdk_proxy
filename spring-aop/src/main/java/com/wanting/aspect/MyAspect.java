package com.wanting.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

    @Pointcut("execution(* com.wanting.service.*.*(..))")
    private void pointCutExecution(){

    }

    @Before("pointCutExecution()")
    private void before(){
        System.out.println("代理来打印的");
    }
}
