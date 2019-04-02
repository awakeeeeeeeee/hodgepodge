package com.awakeee.hodgepodge.designpattern.behavioral.obersver;

//发布消息
public interface Observer {

    void sendMsg(String msg);

    void registerSubject(Subject subject);

    void removeSubject(Subject subject);
}
