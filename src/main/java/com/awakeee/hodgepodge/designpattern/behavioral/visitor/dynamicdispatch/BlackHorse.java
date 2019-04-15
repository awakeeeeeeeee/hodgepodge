package com.awakeee.hodgepodge.designpattern.behavioral.visitor.dynamicdispatch;

public class BlackHorse extends Horse{

    @Override
    void eat(){
        System.out.println("黑马吃草");
    }
}
