package com.slc.design.creational.factory.simple;

/**
 * @author slc
 */
public class Client {


    public static void main(String[] args) {

        Product a = SimpleFactory.createProduct("A");
        Product b = SimpleFactory.createProduct("B");
        a.function();
        b.function();
    }

}
