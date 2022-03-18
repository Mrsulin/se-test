package com.slc.assertkey;


import com.slc.reflect.introspector.Person;
import org.junit.Assert;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        Person person1 = new Person("a", "a", "a");
        Person person2 = new Person("a1", "a", "a");
        System.out.println(person1 .equals(person2) );
        Assert.assertEquals("person类不一致", person1, person2);
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);

    }


}
