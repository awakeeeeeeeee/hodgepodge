package com.awakeee.hodgepodge.designpattern.behavioral.strategy;

public class App {

    public static void main(String[] args) {

        PayStrategy payStrategy = new PayStrategy(new CreditCard());
        payStrategy.pay(200);

        PayStrategy payStrategy1 = new PayStrategy(new Paypal());
        payStrategy1.pay(300);

    }
}
