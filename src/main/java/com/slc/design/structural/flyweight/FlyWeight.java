package com.slc.design.structural.flyweight;


import lombok.AllArgsConstructor;

public interface FlyWeight {

    void operation();
}

@AllArgsConstructor
class ConcreteFlyWeight implements FlyWeight {
    private String name;

    @Override
    public void operation() {
        System.out.println(name + "操作...");
    }
}

class FlyWeightFactory{



}