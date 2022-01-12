package com.fundamentos.springboot.fundamentos.bean;

public class MyOperatonImplement implements MyOperation{

    @Override
    public int sum(int number) {
        return number+1;
    }
}
