package com.awakeee.hodgepodge.designpattern.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

public class ChatMediatorImpl implements ChatMediator {

    List<UserBase> list = new ArrayList<>();

    @Override
    public void addUser(UserBase userBase) {
        list.add(userBase);
    }


    @Override
    public void sendMessage(String msg,UserBase userBase) {
        for(UserBase userBase1 : list){
            if(userBase1 != userBase){
                userBase1.receiveMsg(msg);
            }
        }
    }
}
