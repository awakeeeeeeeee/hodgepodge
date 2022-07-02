package com.awakeee.hodgepodge.thread1.classloader;

public class Singleton {
    //从上往下执行，放在不同的地方，返回结果不同


    public static Singleton singleton = new Singleton();

    static int x = 0;
    static int y;

//    public static Singleton singleton = new Singleton();

    public Singleton(){
        x++;
        y++;
    }

    public static Singleton getInstance(){
        return singleton;
    }


    public static void main(String[] args) {

        Singleton.getInstance();
        System.out.println(Singleton.x);
        System.out.println(Singleton.y);
    }
}
