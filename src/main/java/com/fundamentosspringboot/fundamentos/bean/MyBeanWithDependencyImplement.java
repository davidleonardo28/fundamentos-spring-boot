package com.fundamentosspringboot.fundamentos.bean;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);
    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("Hemos Ingresado al metodo PrintWithDependency");
        int numero =1;
        LOGGER.debug("El número enviado como parametro a la dependencia operation es :"+numero);
        System.out.println(myOperation.sum(numero));
        System.out.println("Desde la implementacion de un Bean con dependencia");
    }
}
