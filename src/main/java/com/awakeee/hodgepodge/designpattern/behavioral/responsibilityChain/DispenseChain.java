package com.awakeee.hodgepodge.designpattern.behavioral.responsibilityChain;

public interface DispenseChain {

    void setNextChain(DispenseChain chain);

    void dispense(Currency currency);
}
