package com.slc.design.structural.prop.basepro;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public interface Paper {

    String color();

    String desc();

}

class Book implements Paper {

    @Override
    public String color() {
        System.out.println("-------------------------黄皮书");
        return "黄皮书";
    }

    @Override
    public String desc() {
        System.out.println("-------------------------教科书");
        return "教科书";
    }
}

class BookProxy implements InvocationHandler {

    Object origin;

    BookProxy(Object origin) {
        this.origin = origin;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");

        Object invoke = method.invoke(origin, args);
        System.out.println("after");
        return invoke;
    }
}

class Test {

    public static void main(String[] args) {
        Book book=new Book();
        Paper bookProxy = (Paper) Proxy.newProxyInstance(Book.class.getClassLoader(), book.getClass().getInterfaces(), new BookProxy(book));
        bookProxy.color();
        System.out.println();
        bookProxy.desc();
    }
}