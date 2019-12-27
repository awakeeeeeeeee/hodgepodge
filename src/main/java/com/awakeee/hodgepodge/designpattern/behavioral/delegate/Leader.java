package com.awakeee.hodgepodge.designpattern.behavioral.delegate;

import java.util.HashMap;
import java.util.Map;

public class Leader {

    private Map<String,IExecutor> executors = new HashMap<>();


    public Leader() {
        executors.put("commandA",new ExecutorA());
        executors.put("commandB",new ExecutorB());
    }


    public void dispatcher(String command){
        executors.get(command).execute();
    }
}
