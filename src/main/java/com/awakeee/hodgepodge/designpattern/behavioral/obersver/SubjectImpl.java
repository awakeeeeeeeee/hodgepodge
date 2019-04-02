package com.awakeee.hodgepodge.designpattern.behavioral.obersver;

public class SubjectImpl implements Subject {

    @Override
    public void revciveMsg(String msg) {
        System.out.println(this + "receiver msg:"+msg);
    }
}
