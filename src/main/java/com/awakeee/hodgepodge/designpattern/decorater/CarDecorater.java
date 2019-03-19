package com.awakeee.hodgepodge.designpattern.decorater;

public class CarDecorater implements Car {

    private Car car;

    public CarDecorater(Car car) {
        this.car = car;
    }

    @Override
    public void assemble() {
        car.assemble();
    }
}
