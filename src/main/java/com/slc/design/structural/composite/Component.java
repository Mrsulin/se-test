package com.slc.design.structural.composite;

public abstract class Component {

    void addChild(Component child){
        throw new RuntimeException("不支持的方法");
    }

    void removeChild(Component child){
        throw new RuntimeException("不支持的方法");
    }

    Component getChild(){
        throw new RuntimeException("不支持的方法");
    }

    public abstract void operate();
}
class Leaf extends   Component{

    @Override
    public void operate() {

    }
}

class Composite extends   Component{


    @Override
    public void addChild(Component child) {

    }

    @Override
    public void removeChild(Component child) {

    }

    @Override
    public Component getChild() {
        return null;
    }

    @Override
    public void operate() {

    }
}