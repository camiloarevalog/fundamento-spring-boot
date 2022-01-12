package com.fundamentos.springboot.fundamentos.bean;

public class MyBean2Implement implements MyBean{
    @Override
    public void print() {
        System.out.println("Implementación propia desde el bean 2");
    }
}
