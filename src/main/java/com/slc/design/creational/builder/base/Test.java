package com.slc.design.creational.builder.base;

public class Test {

    public static void main(String[] args) {
        Builder builder=new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct();
        Production production = builder.build();
    }
}
