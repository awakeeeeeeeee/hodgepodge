package com.awakeee.hodgepodge.designpattern.behavioral.state;

public class TVStopState implements State {

    @Override
    public void action() {
        System.out.println("tv stop !!!");
    }
}
