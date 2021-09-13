package com.slc.design.structural.facade;

/**
 * @author slc
 */
public class Facade {
    private ModuleA moduleA;
    private ModuleB moduleB;
    private ModuleC moduleC;

    public Facade(){
        moduleA=new ModuleA();
        moduleB=new ModuleB();
        moduleC=new ModuleC();
    }

    public  void  operateA(){
        moduleA.operate();
        moduleB.operate();
    }

    public  void  operateB(){
        moduleB.operate();
        moduleC.operate();
    }
}

class ModuleA{

    public void operate(){
        System.out.println("调用模块A的方法");
    }

}
class ModuleB{

    public void operate(){
        System.out.println("调用模块B的方法");
    }

}
class ModuleC{

    public void operate(){
        System.out.println("调用模块C的方法");
    }
}

class Client{
    public static void main(String[] args) {
        Facade facade=new Facade();
        facade.operateA();
        facade.operateB();
    }
}