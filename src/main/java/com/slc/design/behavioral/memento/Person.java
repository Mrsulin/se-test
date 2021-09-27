package com.slc.design.behavioral.memento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 备忘录模式
 * @author sulin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;
    private Integer sex;
    private String address;

    public Memento createMemento(){
        return new Memento(name,sex,address);
    }

    public void  restoreMemento(Memento memento){
        this.name=memento.getName();
        this.sex=memento.getSex();
        this.address=memento.getAddress();
    }
}
@Data
@NoArgsConstructor
@AllArgsConstructor
class Memento{
    private String name;
    private Integer sex;
    private String address;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Caretaker {
    Memento memento;
}

class  Test{

    public static void main(String[] args) {
        Person person = new Person("zs", 1, "成都");
        Memento memento = person.createMemento();
        Caretaker caretaker = new Caretaker(memento);

        person.setName("ll");
        person.setSex(0);
        person.setAddress("北京");

        System.out.println(person);
        person.restoreMemento(caretaker.getMemento());
        System.out.println(person);
    }
}