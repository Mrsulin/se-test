package com.slc.stream;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {

    public static void main(String[] args) {
        print();
    }

    public static void print() {
        List<Person> personList = IntStream.range(1, 100).mapToObj(i -> new Person(String.valueOf(new Random().nextInt()), i)).collect(Collectors.toList());

        personList.stream().filter(i -> i.getAge() % 2 == 0).mapToInt(Person::getAge).forEach(System.out::println);
    }


}
