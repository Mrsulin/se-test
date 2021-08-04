package com.slc.design.creational.builder.base;

public class Director {

    private Builder builder;

    public Director(Builder builder){
        this.builder=builder;
    }

    public void construct(){
        builder.part1("asd1");
        builder.part2("asd2");
        builder.part3("asd3");
    }
}
