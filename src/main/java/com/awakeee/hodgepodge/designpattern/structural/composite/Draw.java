package com.awakeee.hodgepodge.designpattern.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Draw implements Shape {

    private List<Shape> shapeList = new ArrayList<Shape>();

    @Override
    public void draw(String color) {
        for(Shape shape : shapeList){
            shape.draw(color);
        }
    }

    public void addShape(Shape shape){
        shapeList.add(shape);
    }
}
