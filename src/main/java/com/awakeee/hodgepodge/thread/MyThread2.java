package com.awakeee.hodgepodge.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

//计数
public class MyThread2 {

    private int count = 0;

    @Test
    public void test1() throws InterruptedException {
        Process p = new Process();
        for(int i=0;i<20;i++){
            Thread t = new Thread(p);
            t.start();
        }

        Thread.sleep(2000);
        System.out.println("result____: "+count);
    }




    class Process implements Runnable{

        @Override
        public void run() {
            while(count < 1000){
                count++;
                //System.out.println(Thread.currentThread().getName()+"__"+Thread.currentThread().getId()+": "+count);
            }
        }
    }


    private static AtomicInteger num = new AtomicInteger(0);

    public static void increment(){
        num.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        for(int i=0; i< threads.length; i++){
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for(int i=0;i<100;i++){
                        increment();
                    }
                }
            });
            threads[i].start();
        }
        //等待所有累加线程都结束
//        while(Thread.activeCount() > 1){
//            Thread.yield();
//        }
        Thread.sleep(1000);
        System.out.println("num="+num);
    }


    @Test
    public void testnum(){
        Thread[] threads = new Thread[2];
        Process2 p = new Process2();
        for(int i=0; i< threads.length; i++){
//            threads[i] = new Thread(new Runnable() {
//                public void run() {
//                    for(int i=0;i<100;i++){
//                        num.getAndIncrement();
//                    }
//                }
//            });
            threads[i] = new Thread(p);
            threads[i].start();
        }
        //等待所有累加线程都结束
//        while(Thread.activeCount() > 1){
//            Thread.yield();
//        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("num="+num);
    }


    class Process2 implements Runnable{

        @Override
        public void run() {
            for(int i=0;i<100;i++){
                num.getAndIncrement();
                System.out.println(Thread.currentThread().getId() + "___num:" + num.get());
            }
        }
    }
}
