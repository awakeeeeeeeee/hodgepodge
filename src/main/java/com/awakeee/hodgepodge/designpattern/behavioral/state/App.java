package com.awakeee.hodgepodge.designpattern.behavioral.state;

public class App {

    public static void main(String[] args) {

        State state = new TVStartState();
        State state1 = new TVStopState();

        TVContext stateContext = new TVContext(state);
        stateContext.action();

        stateContext = new TVContext(state1);
        stateContext.action();

    }
}
