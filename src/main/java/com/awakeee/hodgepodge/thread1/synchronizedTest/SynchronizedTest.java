package com.awakeee.hodgepodge.thread1.synchronizedTest;

import com.awakeee.hodgepodge.thread1.producerAndconsumer.WhileTest;

public class SynchronizedTest {

    private static int i = 0;

    private static Object object = new Object();

    public static void main(String[] args) {

        MyThread myThread = new MyThread(i);

        Thread t = new Thread(myThread);
        Thread t1 = new Thread(myThread);

        t.start();
        t1.start();

    }



    static class MyThread implements Runnable{

        int i;

        public MyThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
                while (i<10){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized(object){
                        System.out.println(Thread.currentThread().getName() + " run ...." + (i++));
                    }
            }
        }
    }
}
