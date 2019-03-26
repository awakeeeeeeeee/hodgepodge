package com.awakeee.hodgepodge.designpattern.proxy;

public class CommandProxy implements Command {

    private Command command;

    private boolean isAdmin = false;

    public CommandProxy(String name,String password) {
        if(name.equalsIgnoreCase("admin") && password.equalsIgnoreCase("1111")){
            isAdmin = true;
            command = new CommandExecute();
        }
    }

    @Override
    public void execute() {
       if(isAdmin){
           command.execute();
       }else{
            throw new RuntimeException("no right to execute command!!!");
       }
    }
}
