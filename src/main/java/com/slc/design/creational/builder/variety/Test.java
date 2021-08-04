package com.slc.design.creational.builder.variety;

public class Test {

    public static void main(String[] args) {
        Product build = Product.builder().name("zs").password("zs").build();
        System.out.println(build);
    }
}
