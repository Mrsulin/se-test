package com.slc.design.structural.prop;

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
class PersonInvocationHandler<T> implements InvocationHandler{
    private  T t;
    public PersonInvocationHandler(T t){
        this.t=t;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("drink milk");
        method.setAccessible(true);
        Object invoke = method.invoke(t, args);
        return invoke;
    }
}

class Client{

    public static void main(String[] args) {
        Person zs = new Person("zs");
        PersonInvocationHandler<Person> personInvocationHandler = new PersonInvocationHandler<>(zs);
        Man o = (Man) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Man.class}, personInvocationHandler);
        Person p= (Person) o;
        o.sleep();
    }
}