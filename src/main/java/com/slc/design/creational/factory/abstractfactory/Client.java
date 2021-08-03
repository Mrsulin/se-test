package com.slc.design.creational.factory.abstractfactory;

public class Client {

    public static void main(String[] args) {
        AbstractFactory factoryA=new FactoryA();
        factoryA.createBag().function();
        factoryA.createBook().function();
        factoryA.createPen().function();


        AbstractFactory factoryB=new FactoryB();
        factoryB.createBag().function();
        factoryB.createBook().function();
        factoryB.createPen().function();
    }
}
