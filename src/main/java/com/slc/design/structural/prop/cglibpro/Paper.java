package com.slc.design.structural.prop.cglibpro;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Paper {

    public void m1(){
        System.out.println("m1");
    }

    public void m2(){
        System.out.println("m2");
    }
}

class Test{
    public static void main(String[] args) {
        Paper paper=new Paper();
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(paper.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before");
                Object invoke = methodProxy.invokeSuper(o, objects);
                System.out.println("after");
                return invoke;
            }
        });
        Paper o = (Paper) enhancer.create();
        o.m1();
        o.m2();
    }
}