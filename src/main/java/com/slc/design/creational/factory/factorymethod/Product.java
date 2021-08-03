package com.slc.design.creational.factory.factorymethod;
/**
 * @author slc
 */
public interface Product {
    void function();
}

class ProductA implements Product {

    @Override
    public void function() {
        System.out.println("调用 A 产品的功能");
    }
}

class ProductB implements Product {

    @Override
    public void function() {
        System.out.println("调用 B 产品的功能");
    }
}


