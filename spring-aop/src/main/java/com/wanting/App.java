package com.wanting;

import com.wanting.config.AppConfiguration;
import com.wanting.service.IndexService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfiguration.class);

        IndexService bean = (IndexService) applicationContext.getBean("com.wanting.service.IndexServiceImpl");

        bean.index();
    }
}
