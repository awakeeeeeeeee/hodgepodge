package com.awakeee.hodgepodge.designpattern.behavioral.state;

public class TVContext implements State {

    private State state;

    public TVContext(State state) {
        this.state = state;
    }

    @Override
    public void action() {
            state.action();
    }
}
