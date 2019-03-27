package com.awakeee.hodgepodge.designpattern.structural.composite;

public class App {

    public static void main(String[] args) {

        Shape shape = new Circle();
        Shape shape1 = new Triangle();

        Draw draw = new Draw();
        draw.addShape(shape);
        draw.addShape(shape1);

        draw.draw("red");

    }


}
