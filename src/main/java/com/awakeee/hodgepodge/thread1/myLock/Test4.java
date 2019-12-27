package com.awakeee.hodgepodge.thread1.myLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Test4 {

    static ReentrantLock lock = new ReentrantLock();

    static int i=0;

    public static void main(String[] args) {

        Process p = new Process();
        Process1 p1 = new Process1();

        for(int i=0;i<10;i++){
            Thread t = new Thread(p);
            t.start();
        }

//        for(int i=0;i<10;i++){
//            Thread t = new Thread(p1);
//            t.start();
//        }

    }



    static class Process implements Runnable{

        @Override
        public void run() {
            try {
                //锁等待超时，尝试获取锁的线程将不在执行抢锁
                boolean flag = lock.tryLock(1,TimeUnit.SECONDS);
//                System.out.println(flag);
                if(flag){
//                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " is working..." + i++);
                    Thread.sleep(2000);
                    lock.unlock();
                }else {
                    System.out.println(Thread.currentThread().getName() + " time out");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Process1 implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is working..." + i++);
        }
    }
}
