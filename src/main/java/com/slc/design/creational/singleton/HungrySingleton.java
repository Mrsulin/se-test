package com.slc.design.creational.singleton;

import com.slc.design.creational.singleton.entity.Singleton;

/**
 * @author slc
 */
public class HungrySingleton {
    private static Singleton singleton = new Singleton();

    private HungrySingleton() {

    }

    public static Singleton getInstance() {
        return singleton;
    }

}
