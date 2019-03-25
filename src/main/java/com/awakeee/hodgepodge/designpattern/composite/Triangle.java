package com.awakeee.hodgepodge.designpattern.composite;

public class Triangle implements Shape {

    @Override
    public void draw(String color) {
        System.out.println("draw triangle with color:"+color);
    }
}
