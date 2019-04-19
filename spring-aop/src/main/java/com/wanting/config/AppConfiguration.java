package com.wanting.config;

import com.wanting.nameGenerator.MyBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
//nameGenerator 通过该属性可以设置一个 bean name 的生成规则
@ComponentScan(value = "com",nameGenerator = MyBeanNameGenerator.class)
//开启使用aspectJ 方式 aop
//proxyTargetClass 是否使用cglib 形式代理
@EnableAspectJAutoProxy(proxyTargetClass=false)
public class AppConfiguration {

}
