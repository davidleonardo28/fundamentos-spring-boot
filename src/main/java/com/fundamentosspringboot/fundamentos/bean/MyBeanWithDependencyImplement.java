package com.fundamentosspringboot.fundamentos.bean;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        int numero =1;
        System.out.println(myOperation.sum(numero));
        System.out.println("Desde la implementacion de un Bean con dependencia");
    }
}
