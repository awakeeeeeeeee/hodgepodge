package com.awakeee.hodgepodge.designpattern.structural.composite;

public class Circle implements Shape {

    @Override
    public void draw(String color) {
        System.out.println("draw circle with color:"+color);
    }
}
