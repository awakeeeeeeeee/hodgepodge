package com.awakeee.hodgepodge.designpattern.behavioral.delegate;

public class App {

    public static void main(String[] args) {


        Leader leader = new Leader();

        String command = "commandB";

        leader.dispatcher(command);

    }
}
