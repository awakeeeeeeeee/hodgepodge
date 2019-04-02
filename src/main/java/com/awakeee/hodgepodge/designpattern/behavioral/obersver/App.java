package com.awakeee.hodgepodge.designpattern.behavioral.obersver;

public class App {

    public static void main(String[] args) {

        Observer observer = new ObserverImpl();

        Subject subject = new SubjectImpl();
        Subject subject1 = new SubjectImpl();
        Subject subject2 = new SubjectImpl();

        observer.registerSubject(subject);
        observer.registerSubject(subject1);
        observer.registerSubject(subject2);

        observer.sendMsg("hello");

        observer.removeSubject(subject);

        observer.sendMsg("world");
    }
}
