package com.toolbox.factory;

public class BeanFactoryTest {

    public static void main(String[] args) {
        TestService testService = (TestService) BeanFactory.getBean("testService");
        testService.test();
    }
}
