package com.slc.design.behavioral.observable;

import java.util.ArrayList;
import java.util.List;

public interface Observable {
    void register(Observer observer);

    void remove(Observer observer);

    void notify(String name, String msg);

    void notifyAll(String msg);
}

class ConcreteObservable implements Observable {

    List<Observer> list = new ArrayList<>();

    @Override
    public void register(Observer observer) {
        list.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        list.remove(observer);
    }

    @Override
    public void notify(String name, String msg) {
        list.stream().filter(observer -> observer.getName().equals(name)).forEach(observer -> observer.receiveMsg(msg));
    }

    @Override
    public void notifyAll(String msg) {
        list.forEach(observer -> observer.receiveMsg(msg));
    }
}


class Observer {
    public Observer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public void receiveMsg(String msg) {
        System.out.println(this.name + "接受到消息: " + msg);
    }
}

class Test{
    public static void main(String[] args) {
        Observable observable=new ConcreteObservable();
        Observer observerA=new Observer("A");
        Observer observerB=new Observer("B");
        Observer observerC=new Observer("C");
        observable.register(observerA);
        observable.register(observerB);
        observable.register(observerC);

        observable.notify("A","AAAAAAAAAAA");
        observable.notify("B","BBBBBBBBBBB");
        observable.notify("C","ccccccccccc");
        System.out.println("---------------------");
        observable.notifyAll("all");
    }
}