package com.awakeee.hodgepodge.thread1.interrupt;

public class InterruptTest1 {

    private static Object o = new Object();

    //中断线程可以立刻让阻塞线程退出


    public static void main(String[] args) {


//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    System.out.println("线程阻塞被打断..");
//                }
//            }
//        });
//
//        t.start();
//
//        t.interrupt();



//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                synchronized (o){
//                    while (true) {
//                        try {
//                            o.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                            System.out.println("线程阻塞被打断..");
//                        }
//                    }
//                }
//            }
//        });
//
//        t1.start();
//        t1.interrupt();


//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//
//                }
//            }
//        });
//
//        t2.start();
////        t2.interrupt();
//
//        Thread main = Thread.currentThread();
//        main.interrupt();
//
//        try {
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            System.out.println("线程阻塞被打断..");
//        }

    }
}
