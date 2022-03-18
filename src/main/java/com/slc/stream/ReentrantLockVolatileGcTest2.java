package com.slc.stream;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ReentrantLockVolatileGcTest2 {
    static List<ReentrantLockVolatileGcTest.Person> personList = new ArrayList<>();

    static {
        for (int i = 1; i <= 21; i++) {
            personList.add(new ReentrantLockVolatileGcTest.Person("zs" + i, i % 3 + 20));
        }
    }

    @org.junit.Test
    @SneakyThrows
    public void test2() {
        for (int i=0;i<personList.size();i++){
            ReentrantLockVolatileGcTest.Person person=personList.get(i);
            if (person.getName().contains("3")) {
                personList.remove(person);
            }
        }
        System.out.println(personList);
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
    @org.junit.Test
    @SneakyThrows
    public void test3() {
//        for (Test.Person person : personList) {
//            if (person.getName().contains("3")) {
//                personList.remove(person);
//            }
//        }
//        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);

        Map<Object,Object> map=new HashMap<>();
        map.put(null,1);
        map.put(1,null);
        System.out.println(map.get(1));
    }


}
