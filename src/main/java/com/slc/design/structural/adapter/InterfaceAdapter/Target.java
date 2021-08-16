package com.slc.design.structural.adapter.InterfaceAdapter;

import java.awt.event.KeyAdapter;

public interface Target {

    void request();

    void otherMethod1();

    void otherMethod2();
}

abstract class TempAdapter implements Target{

    @Override
    public void request(){ }

    @Override
    public void otherMethod1() { }

    @Override
    public void otherMethod2() { }
}

class Adapter extends TempAdapter{
    Other other;
    Adapter(Other other){
        this.other=other;
    }

    @Override
    public void request() {
        other.otherImpRequest();
    }
}

class Other{

    public void otherImpRequest(){

        System.out.println("被适配类中  其他作用的request方法");
    }
}

class Test{
    public static void main(String[] args) {
        Target other=new Adapter(new Other());
        other.request();
    }
}