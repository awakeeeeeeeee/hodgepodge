package com.awakeee.hodgepodge.designpattern.behavioral.delegate;

public class ExecutorB implements IExecutor {

    @Override
    public void execute() {
        System.out.println("B 开始执行...");
    }
}
