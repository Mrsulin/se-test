package com.slc.design.behavioral.command;

public interface Command {
    void execute();
}

class VegetableCommand implements  Command{

    Receiver  receiver;

    VegetableCommand(Receiver receiver){
        this.receiver=receiver;
    }

    @Override
    public void execute() {
        receiver.makeVegetables();
    }
}
class MeatCommand implements  Command{
    Receiver  receiver;

    MeatCommand(Receiver receiver){
        this.receiver=receiver;
    }
    @Override
    public void execute() {
       receiver.makeMeat();
    }
}

class Receiver{

    public void makeVegetables(){
        System.out.println("做蔬菜");
    }

    public void makeMeat(){
        System.out.println("做肉");
    }
}

class Waiter{

    private VegetableCommand vegetableCommand;
    private MeatCommand meatCommand;

    public Waiter(VegetableCommand vegetableCommand, MeatCommand meatCommand) {
        this.vegetableCommand = vegetableCommand;
        this.meatCommand = meatCommand;
    }

    public void executeVegetables(){
        vegetableCommand.execute();
    }

    public void executeMeat(){
        meatCommand.execute();
    }
}

class Test{

    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Waiter waiter = new Waiter(new VegetableCommand(receiver),new MeatCommand(receiver));
        waiter.executeMeat();
        waiter.executeVegetables();
    }
}