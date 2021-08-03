package com.slc.design.creational.factory.factorymethod;

/**
 * @author slc
 */
public class Client {

    public static void main(String[] args) {
        AbstractFactory factoryA=new FactoryA();
        AbstractFactory factoryB=new FactoryB();
        createProduct(factoryA).function();
        createProduct(factoryB).function();
    }

    public static Product createProduct(AbstractFactory factory){
        return factory.createProduct();
    }
}
