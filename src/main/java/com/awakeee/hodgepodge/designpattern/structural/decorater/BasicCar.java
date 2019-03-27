package com.awakeee.hodgepodge.designpattern.structural.decorater;

public class BasicCar implements Car {
    @Override
    public void assemble() {
        System.out.print("basic car");
    }
}
