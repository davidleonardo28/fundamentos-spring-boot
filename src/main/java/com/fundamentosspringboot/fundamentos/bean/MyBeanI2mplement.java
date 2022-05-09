package com.fundamentosspringboot.fundamentos.bean;

import org.springframework.stereotype.Component;

@Component("myBean2")
public class MyBeanI2mplement implements MyBean{

    @Override
    public void print() {
        System.out.println("Mi implementación 2 propia del Bean 2");
    }
}
