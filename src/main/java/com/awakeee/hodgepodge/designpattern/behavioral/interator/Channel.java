package com.awakeee.hodgepodge.designpattern.behavioral.interator;

public class Channel {

    private double frequency;

    private String name;

    public Channel(double frequency, String name) {
        this.frequency = frequency;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "frequency=" + frequency +
                ", name='" + name + '\'' +
                '}';
    }
}
