package com.slc.design.structural.adapter.ClassAdapter;

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

class adapter extends Adaptee implements Target{

    @Override
    public void request() {
        super.otherImpRequest();
    }
}

class Test{

    public static void main(String[] args) {
        Target other=new adapter();
        other.request();
    }
}