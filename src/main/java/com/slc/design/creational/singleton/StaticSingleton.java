package com.slc.design.creational.singleton;

/**
 * @author sulin
 */
public class StaticSingleton {

    private StaticSingleton(){

    }

    public Singleton getInstance(){
        return SingletonFactory.singleton;

    }

    private static class SingletonFactory{
        private static final Singleton singleton=new Singleton();
    }
}
