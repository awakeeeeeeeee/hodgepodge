package com.awakeee.hodgepodge.designpattern.decorater;

public class App {


    public static void main(String[] args) {

        Car sportscar = new CarDecorater(new SportsCar(new BasicCar()));

        Car sportslexuscar = new CarDecorater(new LexusCar(new SportsCar(new BasicCar())));

        sportscar.assemble();

        System.out.println("\n");

        sportslexuscar.assemble();




    }
}
