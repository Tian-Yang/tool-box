package com.toolbox.factory;

import org.springframework.context.annotation.Bean;

public class BeanFactoryTest {

    public static void main(String[] args) {
        TestService testService = (TestService) BeanFactory.getBean("testService");
        testService.test();
    }
}
