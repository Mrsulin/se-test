package com.slc.design.structural.composite;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Component {

    void addChild(Component child) {
        throw new RuntimeException("不支持的方法");
    }

    void removeChild(Component child) {
        throw new RuntimeException("不支持的方法");
    }

    List<Component> getChild() {
        throw new RuntimeException("不支持的方法");
    }

    public abstract void operate();
}
@AllArgsConstructor
class Leaf extends Component {
    private String name;
    @Override
    public void operate() {
        System.out.println("调用Leaf operate 方法");
    }


}
@ToString(exclude = "name")
class Composite extends Component {

    @Override
    public void addChild(Component child) {
        children.add(child);
    }

    @Override
    public void removeChild(Component child) {
        children.remove(child);
    }

    @Override
    public List<Component> getChild() {
        return children;
    }

    @Override
    public void operate() {
        System.out.println("调用 Composite operate 方法");
    }

    public Composite(String name) {
        this.name = name;
    }
    private List<Component> children = new ArrayList<>();

    private String name;
}

class Test {

    public static void main(String[] args) {
        Component component = new Composite("composite root");
        Composite levelSecondComposite = new Composite("composite level 1");
        levelSecondComposite.addChild(new Leaf("leaf level 2:A"));
        levelSecondComposite.addChild(new Leaf("leaf level 2:B"));
        component.addChild(levelSecondComposite);
        component.addChild(new Leaf("leaf level 1:A"));
        component.addChild(new Leaf("leaf level 1:B"));

        System.out.println("#####################");
        List<Component> child = component.getChild();
        child.forEach(System.out::println);
        System.out.println("#####################");
        child.forEach(Component::operate);
        System.out.println("#####################");
    }
}