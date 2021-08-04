package com.slc.design.creational.singleton;

import com.slc.design.creational.singleton.entity.Singleton;

/**
 * @author slc
 */
public class DoubleCheckSingleton {

    private DoubleCheckSingleton() {

    }

    private static volatile Singleton singleton = null;

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (singleton==null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
