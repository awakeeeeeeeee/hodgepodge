package com.awakeee.hodgepodge.designpattern.structural.proxy;

import java.lang.reflect.Proxy;

public class App {

    public static void main(String[] args) {

//        Command command = new CommandProxy("111","111");
//        command.execute();

        Command command = new CommandProxy("admin","1111");
        command.execute();

        //动态代理
        Command command1 = new CommandExecute();
        Command command2 = (Command) Proxy.newProxyInstance(Command.class.getClassLoader(),
                new Class[]{Command.class}, new DynamicProxy(command1));
        command2.execute();

    }
}
