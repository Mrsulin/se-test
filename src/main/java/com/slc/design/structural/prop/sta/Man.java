package com.slc.design.structural.prop.sta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public interface Man{
    public int sleep();
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person implements Man {
    private String name;
    @Override
    public int sleep(){
        System.out.printf(" sleep ...in %d clock\n",8);
        return 8;
    }
}
class PersonInvocationHandler implements InvocationHandler{

    Man man;

    public PersonInvocationHandler(Man man) {
        this.man = man;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.setAccessible(true);
        System.out.println("before ....");
        Object invoke = method.invoke(man, args);
        System.out.println("after ....");
        return invoke;
    }
}

class Client{

    public static void main(String[] args) {
        Person zs = new Person("zs");
        PersonInvocationHandler personInvocationHandler = new PersonInvocationHandler(zs);
        Man o = (Man) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Man.class}, personInvocationHandler);
        o.sleep();
    }
}