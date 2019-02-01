package com.awakeee.hodgepodge.thread.communication;

import org.junit.Test;

public class ThreadLocalExample {

    private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

    private int num=0;


    //线程0对threadlocal的修改,线程1中是看不到的,同时main线程中也看不到线程1对threadlocal的修改
    //线程0，线程1和main线程都可以看到对num对修改
    @Test
    public void test() throws InterruptedException {

       threadLocal.set(0);

        Process p = new Process();
        Process1 p1 = new Process1();
        Thread t = new Thread(p);
        Thread t1 = new Thread(p1);
        t.start();

        Thread.sleep(2000);

        t1.start();
        t1.join();

        System.out.println("线程"+Thread.currentThread().getName()+"获取到的threadlocal数据为:"+threadLocal.get());
        System.out.println("线程"+Thread.currentThread().getName()+"获取到的num数据为:"+num);
    }




    class Process implements Runnable{

        @Override
        public void run() {

            threadLocal.set(100);
            num = 10;
            System.out.println("线程"+Thread.currentThread().getName()+"获取到的threadlocal数据为:"+threadLocal.get());
            System.out.println("线程"+Thread.currentThread().getName()+"获取到的num数据为:"+num);

        }
    }

    class Process1 implements Runnable{

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"获取到的threadlocal数据为:"+threadLocal.get());
            System.out.println("线程"+Thread.currentThread().getName()+"获取到的num数据为:"+num);
        }
    }
}
