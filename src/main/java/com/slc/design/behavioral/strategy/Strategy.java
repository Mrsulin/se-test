package com.slc.design.behavioral.strategy;

import lombok.Data;

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

@Data
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
        contextA.operate();

        contextA.setStrategy(new StrategyB());
        contextA.operate();

        contextA.setStrategy(new StrategyC());
        contextA.operate();
    }
}