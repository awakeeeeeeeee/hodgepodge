package com.awakeee.hodgepodge.designpattern.behavioral.mediator;

public abstract class UserBase {

    protected String name;

    protected ChatMediator chatMediator;

    public UserBase(String name, ChatMediator chatMediator) {
        this.name = name;
        this.chatMediator = chatMediator;
    }

    abstract void sendMsg(String msg);

    abstract void receiveMsg(String msg);

    void sendMessage(String msg){
    }

}
