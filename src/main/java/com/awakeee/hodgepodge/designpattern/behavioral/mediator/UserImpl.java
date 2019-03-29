package com.awakeee.hodgepodge.designpattern.behavioral.mediator;

public class UserImpl extends UserBase {


    public UserImpl(String name, ChatMediator chatMediator) {
        super(name, chatMediator);
    }

    @Override
    void sendMsg(String msg) {
        System.out.println(this.name + " send msg:"+msg);
        chatMediator.sendMessage(msg,this);
        //通过聊天平台发送消息
    }

    @Override
    void receiveMsg(String msg) {
        System.out.println(this.name + " receive msg:"+msg);
    }
}
