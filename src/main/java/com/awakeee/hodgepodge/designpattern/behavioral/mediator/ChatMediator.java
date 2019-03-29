package com.awakeee.hodgepodge.designpattern.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

//聊天平台
public interface ChatMediator {

    void addUser(UserBase userBase);

    void sendMessage(String msg,UserBase userBase);

}
