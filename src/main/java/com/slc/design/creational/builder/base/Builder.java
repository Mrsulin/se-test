package com.slc.design.creational.builder.base;

public interface Builder {
    void part1(String part1);
    void part2(String part2);
    void part3(String part3);
    Production build();
}

class ConcreteBuilder implements Builder{

    Production production=new Production();


    @Override
    public void part1(String part1) {
        production.setPart1(part1);
    }

    @Override
    public void part2(String part2) {
        production.setPart2(part2);

    }
    @Override
    public void part3(String part3) {
        production.setPart3(part3);
    }
    @Override
    public Production build(){
        return this.production;
    }
}