package com.slc.design.structural.decorator;


public interface Component {

    Integer operate();
}

class ConcreteComponent implements Component {

    @Override
    public Integer operate() {
        System.out.println("base content：" + 100);
        return 100;
    }
}

abstract class Decorator implements Component {

    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }
}

class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public Integer operate() {
        System.out.println("ConcreteDecoratorA add " + 10);
        return component.operate() + 10;
    }
}

class ConcreteDecoratorB extends Decorator {
    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public Integer operate() {
        System.out.println("ConcreteDecoratorB add " + 1);
        return component.operate() + 1;
    }
}

class Test {

    public static void main(String[] args) {
        Component component = new ConcreteDecoratorB(new ConcreteDecoratorA(new ConcreteComponent()));
        System.out.println();
        System.out.println(component.operate());
    }
}