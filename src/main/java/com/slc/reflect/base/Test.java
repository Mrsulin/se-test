package com.slc.reflect.base;

import sun.reflect.Reflection;

public class Test {

    @org.junit.Test
    public void test(){
        Reflection.getCallerClass();
    }
}
