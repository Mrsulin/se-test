package com.slc.design.creational.factory.factorymethod;

/**
 * @author slc
 */
public interface AbstractFactory {

    public Product createProduct();
}

class FactoryA implements  AbstractFactory{

    @Override
    public Product createProduct() {
        return new ProductA();
    }
}


class FactoryB implements  AbstractFactory{

    @Override
    public Product createProduct() {
        return new ProductB();
    }
}