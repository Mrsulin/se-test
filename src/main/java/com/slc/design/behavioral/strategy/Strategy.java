package com.slc.design.behavioral.strategy;

public interface Strategy {
    public void operate();
}
class StrategyA implements Strategy{

    @Override
    public void operate() {
        System.out.println(" 算法A  ");
    }
}
class StrategyB implements Strategy{

    @Override
    public void operate() {
        System.out.println(" 算法B  ");
    }
}
class StrategyC implements Strategy{

    @Override
    public void operate() {
        System.out.println(" 算法C  ");
    }
}

class Context {
    Strategy strategy;
    Context(Strategy strategy){
        this.strategy=strategy;
    }
    public void operate(){

        strategy.operate();
    }
}

class Client{

    public static void main(String[] args) {
        Context contextA = new Context(new StrategyA());
        Context contextB = new Context(new StrategyB());
        Context contextC = new Context(new StrategyC());

        contextA.operate();
        contextB.operate();
        contextC.operate();
    }
}