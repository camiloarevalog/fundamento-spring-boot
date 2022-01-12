package com.fundamentos.springboot.fundamentos.bean;

import com.fundamentos.springboot.fundamentos.FundamentosApplication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanDependencyImplement implements MyBeanDependency{
    private MyOperation myOperation;
    Log LOGGER= LogFactory.getLog(MyBeanDependencyImplement.class);

    public MyBeanDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printDependency() {
        LOGGER.info("Hemos ingresado almetodo de printDependency");
        int numero=8;
        System.out.println("Suma "+ myOperation.sum(numero));
        LOGGER.debug("El númeroneivado como parametro aladependencia operation es:"+numero);
        System.out.println("Hola desde un bean con dependecia");
    }
}
