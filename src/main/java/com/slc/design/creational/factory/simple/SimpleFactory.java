package com.slc.design.creational.factory.simple;

/**
 * 简单工厂
 * @author slc
 */
public class SimpleFactory {
    
    public static Product createProduct(String productName){
        if(productName.equals("A")){
            return new ProductA();
        }

        if(productName.equals("B")){
            return new ProductB();
        }

        throw  new RuntimeException("无相关产品");
    }

}