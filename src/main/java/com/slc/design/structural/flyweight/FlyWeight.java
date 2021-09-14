package com.slc.design.structural.flyweight;


import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

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

class FlyWeightFactory {

    private static Map<String, ConcreteFlyWeight> map = new HashMap<>();


    public static ConcreteFlyWeight getFlyWeight(String name) {
        if (map.get(name) != null) {
            return map.get(name);
        } else {
            ConcreteFlyWeight concreteFlyWeight = new ConcreteFlyWeight(name);
            map.put(name, concreteFlyWeight);
            return concreteFlyWeight;
        }

    }
}