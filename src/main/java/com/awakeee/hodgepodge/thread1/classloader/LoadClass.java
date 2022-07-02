package com.awakeee.hodgepodge.thread1.classloader;

public class LoadClass {

    private static boolean init = false;

    public static void main(String[] args) throws InterruptedException {

//        System.out.println(System.getProperty("sun.boot.class.path"));
//        System.out.println(System.getProperty("java.ext.dirs"));

         new Thread(new Runnable() {
             @Override
             public void run() {
                 while (!init){
                     System.out.println(".");
                 }
             }
         }).start();


        Thread.sleep(2000);


        new Thread(new Runnable() {
            @Override
            public void run() {
                init = true;
                System.out.println("init set true..");
            }
        }).start();

    }





}


