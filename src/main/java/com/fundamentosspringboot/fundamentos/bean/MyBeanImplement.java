package com.fundamentosspringboot.fundamentos.bean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("myBean")
public class MyBeanImplement  implements MyBean{

    @Override
    public void print() {
        System.out.println("Mi implementación propia del Bean");
    }
}
