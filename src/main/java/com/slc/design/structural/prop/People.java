package com.slc.design.structural.prop;

/**
 * @author sulin
 */
public interface People {

    void say();
}

class Boy implements People {


    @Override
    public void say() {
        System.out.println("boy say hello");
    }
}

class BoyProxy implements People {

    Boy boy;

    public BoyProxy() {
        boy = new Boy();
    }

    @Override
    public void say() {
        System.out.println(" boy say before");
        boy.say();
        System.out.println(" boy say after");
    }
}
class Test{

    public static void main(String[] args) {
        People boyProxy=new BoyProxy();
        boyProxy.say();
    }
}