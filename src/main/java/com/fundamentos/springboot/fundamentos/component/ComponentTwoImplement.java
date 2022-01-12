package com.fundamentos.springboot.fundamentos.component;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements ComponentDependency {

    @Override
    public void saludar() {
        System.out.println("Hola mundo desde el componente 2");
    }
}
