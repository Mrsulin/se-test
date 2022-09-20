package com.slc.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        integers.forEach(ii -> list.add(() -> getEarlyData(ii)));
        Object object = list.get(0).getObject();
        list.forEach(e-> System.out.println(e.getObject()));

    }


    static List<ObjectFactory> list = new ArrayList<>();

    public static void add(ObjectFactory factory) {
        list.add(factory);
    }

    public static Object getEarlyData(int i) {
        return i * 2;
    }
}

@FunctionalInterface
interface ObjectFactory {

    Object getObject();
}
