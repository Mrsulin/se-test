package com.slc.design.creational.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author slc
 */
public class StaticSingleton {

    public static void main(String[] args) {

        List list = new ArrayList();

        if (list.size()>0 && list.get(0) != null) {
            System.out.println(1);
        }
    }
}
