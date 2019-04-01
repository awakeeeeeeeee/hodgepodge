package com.awakeee.hodgepodge.designpattern.behavioral.responsibilityChain;

public class Dollar20Dispenser implements DispenseChain {

    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain chain) {
        this.chain = chain;
    }

    @Override
    public void dispense(Currency currency) {

        if(currency.getAmount()>=20){
            int num = currency.getAmount()/20;
            int reminder = currency.getAmount()%20;
            System.out.println("Dispensing "+num+" 20$ note");
            if(reminder != 0){
                this.chain.dispense(new Currency(reminder));
            }
        }else{
            this.chain.dispense(currency);
        }
    }
}
