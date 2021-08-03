package com.slc.design.creational.factory.abstractfactory;

public interface Pen {
    void function();
}
interface Book {
    void function();
}
interface Bag {
    void function();
}

class Apen implements Pen {

    @Override
    public void function() {
        System.out.println(" A 厂 Pen");
    }
}

class Abook implements Book {

    @Override
    public void function() {
        System.out.println(" A 厂 Book");
    }
}

class Abag implements Bag {

    @Override
    public void function() {
        System.out.println(" A 厂 Bag");
    }
}

class Bpen implements Pen {

    @Override
    public void function() {
        System.out.println(" B 厂 Pen");
    }
}

class Bbook implements Book {

    @Override
    public void function() {
        System.out.println(" B 厂 Book");
    }
}

class Bbag implements Bag {

    @Override
    public void function() {
        System.out.println(" B 厂 Bag");
    }
}