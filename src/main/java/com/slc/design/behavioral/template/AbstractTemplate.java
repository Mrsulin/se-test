package com.slc.design.behavioral.template;

public abstract class AbstractTemplate {

    public void completeOperate() {
        first();
        second();
        third();
    }

    public void first() {
        System.out.println(" first process");
    }

    public abstract void second();

    public void third() {
        System.out.println(" third process");
    }
}

class ConcreteA extends AbstractTemplate {

    @Override
    public void second() {
        System.out.println(" process A ");
    }
}

class ConcreteB extends AbstractTemplate {

    @Override
    public void second() {
        System.out.println(" process B ");
    }
}
class Test{

    public static void main(String[] args) {
        AbstractTemplate a=new ConcreteA();
        a.completeOperate();

        System.out.println("---------------------------------");
        AbstractTemplate b=new ConcreteB();
        b.completeOperate();
    }
}