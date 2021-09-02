package com.slc.design.structural.bridge;

import lombok.Getter;
import lombok.Setter;

/**
 * @author slc
 */
@Getter
@Setter
public abstract class Bridge {

    protected SourceAble sourceAble;

    public abstract void operate();
}

class ConcreteBridge extends Bridge{

    @Override
    public void operate() {
        //other operation
        getSourceAble().operate();
    }
}

interface SourceAble {

    void operate();
}

class SourceSubA implements SourceAble {
    @Override
    public void operate() {
        System.out.println("服务A 操作...");
    }
}
class SourceSubB implements SourceAble {
    @Override
    public void operate() {
        System.out.println("服务B 操作...");
    }
}

class Test{

    public static void main(String[] args) {
        Bridge bridge=new ConcreteBridge();

        bridge.setSourceAble(new SourceSubA());
        bridge.operate();

        bridge.setSourceAble(new SourceSubB());
        bridge.operate();
    }
}
