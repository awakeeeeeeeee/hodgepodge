package com.awakeee.hodgepodge.designpattern.decorater;

public class LexusCar extends CarDecorater{
    public LexusCar(Car car) {
        super(car);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.print(" adding feature lexus car");
    }
}
