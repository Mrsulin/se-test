package com.slc.design.creational.builder.variety;

public class Product {

    private String name;
    private String password;
    private String id;

    public static ProductBuilder builder(){
        return new ProductBuilder();
    }

    public static class ProductBuilder{

        private String name;
        private String password;
        private String id;


    }
}
