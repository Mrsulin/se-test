package com.slc.design.creational.factory.abstractfactory;

public interface AbstractFactory {
    Pen createPen();

    Book createBook();

    Bag createBag();
}

class FactoryA implements AbstractFactory{

    @Override
    public Pen createPen() {
        return new Apen();
    }

    @Override
    public Book createBook() {
        return new Abook();
    }

    @Override
    public Bag createBag() {
        return new Abag();
    }
}


class FactoryB implements AbstractFactory{

    @Override
    public Pen createPen() {
        return new Bpen();
    }

    @Override
    public Book createBook() {
        return new Bbook();
    }

    @Override
    public Bag createBag() {
        return new Bbag();
    }
}