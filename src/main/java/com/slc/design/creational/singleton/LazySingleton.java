package com.slc.design.creational.singleton;

import com.slc.design.creational.singleton.entity.Singleton;

/**
 * 线程不安全
 * @author slc
 */
public class LazySingleton {

    private LazySingleton(){

    }
    private static Singleton singleton =null;

    public static Singleton getInstance(){
        if(singleton ==null){
            singleton =  new Singleton("zs");
        }
        return singleton;
    }
}
