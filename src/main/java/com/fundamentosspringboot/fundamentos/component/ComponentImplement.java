package com.fundamentosspringboot.fundamentos.component;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component("componentImplement")
public class ComponentImplement implements ComponentDependency {

    @Override
    public void saludar() {
        System.out.println("Mi primer Componente");
    }
}
