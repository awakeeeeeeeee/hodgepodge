package com.awakeee.hodgepodge.designpattern.behavioral.state;

public class TVStartState implements State {

    @Override
    public void action() {
        System.out.println("tv start!!");
    }
}
