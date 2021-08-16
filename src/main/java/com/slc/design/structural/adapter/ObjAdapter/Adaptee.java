package com.slc.design.structural.adapter.ObjAdapter;

/**
 * @author sulin
 */
public class Adaptee {

    public void otherImpRequest(){

        System.out.println("被适配类中  其他作用的request方法");
    }
}

interface Target{
    public void request();
}

class Adapter implements Target{
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee){
        this.adaptee=adaptee;
    }

    @Override
    public void request() {
        adaptee.otherImpRequest();
    }
}

class Test{

    public static void main(String[] args) {
        Target other=new Adapter(new Adaptee());
        other.request();
    }
}