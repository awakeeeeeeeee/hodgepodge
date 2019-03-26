package com.awakeee.hodgepodge.designpattern.proxy;

public class CommandExecute implements Command {

    @Override
    public void execute() {
        System.out.println("execute command......");
    }
}
