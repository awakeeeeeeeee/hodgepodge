package com.awakeee.hodgepodge.designpattern.decorater;

public class SportsCar extends CarDecorater {

    public SportsCar(Car car) {
        super(car);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.print(" adding feature sports car");
    }
}
