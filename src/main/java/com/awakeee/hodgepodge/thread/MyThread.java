package com.awakeee.hodgepodge.thread;


import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

//计数 num线程不安全 count线程安全
public class MyThread {

    private int num = 0;

    private AtomicInteger count = new AtomicInteger(0);

    @Test
    public void testnum() throws InterruptedException {

        Process p = new Process();
        for(int i=0;i<5;i++){
            Thread t = new Thread(p);
//            t.setDaemon(true);
            t.start();
        }

        Process2 p2 = new Process2();
        for(int i=0;i<5;i++){
            new Thread(p2).start();
        }



        Thread.sleep(1000);

        System.out.println("num:"+num);
        System.out.println("count:"+count.get());

    }

    class Process implements Runnable{

        @Override
        public void run() {
            for(int i=0;i<100;i++){
                num++;
                count.incrementAndGet();
            }
        }
    }

    class Process2 extends Thread{

        @Override
        public void run() {
            for(int i=0;i<100;i++){
                num++;
                count.incrementAndGet();
            }
        }

    }

}