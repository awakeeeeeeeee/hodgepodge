package com.awakeee.hodgepodge.designpattern.behavioral.delegate;

public class ExecutorA implements IExecutor {

    @Override
    public void execute() {
        System.out.println("A 开始执行...");
    }
}
