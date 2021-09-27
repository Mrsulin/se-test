package com.slc.design.behavioral.mediator;

import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 *
 * 中介者模式
 * @author sulin
 */
@AllArgsConstructor
public abstract class Colleague {
    protected Mediator mediator;
    public abstract void sendMsg(String msg);
    public abstract void notifyMsg(String msg);
}

class ConcreteColleague1 extends Colleague {

    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void sendMsg(String msg) {
        mediator.send(msg, this);
    }

    @Override
    public void notifyMsg(String msg) {
        System.out.println("同事1获得消息 " + msg);
    }

}

class ConcreteColleague2 extends Colleague {

    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }


    @Override
    public void sendMsg(String msg) {
        mediator.send(msg, this);
    }

    @Override
    public void notifyMsg(String msg) {
        System.out.println("同事2获得消息 " + msg);
    }
}

interface Mediator {
    void send(String message, Colleague colleague);
}

@Setter
class ConcreteMediator implements Mediator {
    ConcreteColleague1 concreteColleague1;
    ConcreteColleague2 concreteColleague2;


    @Override
    public void send(String message, Colleague colleague) {
        if (colleague == concreteColleague1) {
            concreteColleague1.notifyMsg(message);
        }

        if (colleague == concreteColleague2) {
            concreteColleague2.notifyMsg(message);
        }
    }
}

class Test {
    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();
        ConcreteColleague1 c1 = new ConcreteColleague1(mediator);
        ConcreteColleague2 c2 = new ConcreteColleague2(mediator);

        mediator.setConcreteColleague1(c1);
        mediator.setConcreteColleague2(c2);
        c1.sendMsg("asd");
        c2.sendMsg("nini");
    }
}