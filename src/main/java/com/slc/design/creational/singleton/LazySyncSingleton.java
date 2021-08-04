package com.slc.design.creational.singleton;

import com.slc.design.creational.singleton.entity.Singleton;

/**
 * 线程安全 但每次都要同步
 * @author slc
 */
public class LazySyncSingleton {

    private LazySyncSingleton() {

    }

    private static Singleton singleton = null;

    public static synchronized Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
