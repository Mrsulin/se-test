package com.slc.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReentrantLockVolatileGcTest {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person implements Comparable<Person> {

        private String name;
        private int age;

        @Override
        public int compareTo(Person o) {
            if (o.getAge() >= this.age) {
                return 1;
            }
            return -1;
        }
    }


    @org.junit.Test
    public void test2() {
        List<Person> personList = new ArrayList<>();
        for (int i = 1; i <= 21; i++) {
            personList.add(new Person("zs" + i, i % 3 + 20));
        }
        Stream<Person> stream = personList.stream();
        Stream<Person> personFilterStream1 = stream.filter(p -> p.getAge() != 20);
        Stream<Person> personFilterStream2 = personFilterStream1.filter(p -> !(Integer.parseInt(p.getName().substring(2)) % 2 == 0));
        Stream<Person> distinctStream = personFilterStream2.distinct();
        Stream<Person> sortedStream = distinctStream.sorted();
        List<Person> complicatedList = sortedStream.collect(Collectors.toList());
        System.out.println();
    }

    @org.junit.Test
    public void test3() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Integer integer1 = list.stream().reduce((integer, integer2) -> Integer.sum(integer2, integer)).get();
        System.out.println(integer1);
    }

    @org.junit.Test
    public void test4() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        Lock lock = new ReentrantLock();
        IntStream.range(0, 10000).forEach(list1::add);
        Object obj=new Object();
        IntStream.range(0, 10000).parallel().forEach(i -> {
            synchronized (obj) {
                list2.add(i);
            }
        });
        IntStream.range(0, 10000).parallel().filter(i->i%2==0).forEach(i -> {
            lock.lock();
            try {
                list3.add(i);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        System.out.println(list1.size());
        System.out.println(list2.size());
        System.out.println(list3.size());
    }

    public static class Car extends WeakReference<Car>{

        public Car(Car referent) {
            super(referent);
        }

        public Car(Car referent, ReferenceQueue<? super Car> q) {
            super(referent, q);
        }
    }
}
