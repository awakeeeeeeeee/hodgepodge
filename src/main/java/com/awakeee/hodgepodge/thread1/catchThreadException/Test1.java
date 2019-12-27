package com.awakeee.hodgepodge.thread1.catchThreadException;

public class Test1 {

    public static void main(String[] args) {



        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    System.out.println(3/0);
                }catch (Exception e){
//                    e.printStackTrace();
                    throw e;
                }
            }
        });


        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("t: "+t);
                System.out.println(e);
            }
        });

        t.start();
    }
}
