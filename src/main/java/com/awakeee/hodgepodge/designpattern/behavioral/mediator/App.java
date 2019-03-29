package com.awakeee.hodgepodge.designpattern.behavioral.mediator;

public class App {

    public static void main(String[] args) {

        ChatMediator chatMediator = new ChatMediatorImpl();

        UserBase userBase = new UserImpl("bill",chatMediator);
        UserBase userBase1 = new UserImpl("lili",chatMediator);
        UserBase userBase2 = new UserImpl("steven",chatMediator);

        chatMediator.addUser(userBase);
        chatMediator.addUser(userBase1);
        chatMediator.addUser(userBase2);

        userBase.sendMsg("hi all");
    }
}
