package com.awakeee.hodgepodge.designpattern.behavioral.strategy;

public class CreditCard implements Strategy {

    @Override
    public void pay(int amount) {
        System.out.println(amount + "paid with creditcard");
    }
}
