package com.awakeee.hodgepodge.designpattern.behavioral.strategy;

public  class PayStrategy{

    private Strategy strategy;

    public PayStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    void pay(int amount){
        strategy.pay(amount);
    }
}
