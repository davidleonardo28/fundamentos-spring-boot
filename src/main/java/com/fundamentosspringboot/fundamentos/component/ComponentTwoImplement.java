package com.fundamentosspringboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component("componentTwoImplement")
public class ComponentTwoImplement implements ComponentDependency{
    @Override
    public void saludar() {
        System.out.println("Desde mi componente Dos");
    }
}
