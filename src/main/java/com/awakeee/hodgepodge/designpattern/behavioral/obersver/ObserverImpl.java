package com.awakeee.hodgepodge.designpattern.behavioral.obersver;

import java.util.ArrayList;
import java.util.List;

public class ObserverImpl implements Observer {

    private List<Subject> subjects = new ArrayList<>();

    @Override
    public void sendMsg(String msg) {
        for(Subject subject : subjects){
            subject.revciveMsg(msg);
        }
    }

    @Override
    public void registerSubject(Subject subject) {
        subjects.add(subject);
    }

    @Override
    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }
}
