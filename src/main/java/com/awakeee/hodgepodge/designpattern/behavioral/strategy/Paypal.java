package com.awakeee.hodgepodge.designpattern.behavioral.strategy;

public class Paypal implements Strategy {
    @Override
    public void pay(int amount) {
        System.out.println(amount + "paid with paypal");
    }
}
