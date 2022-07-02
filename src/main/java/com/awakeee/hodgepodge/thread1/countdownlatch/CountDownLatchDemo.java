package com.awakeee.hodgepodge.thread1.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 计数器---减减
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        /**
         * 主线程等待多个子线程做完自己的事情后，才能继续执行
         */

        CountDownLatch countDownLatch = new CountDownLatch(3);

        for(int i=0;i<5;i++){
            new Thread(()->{

                System.out.println(Thread.currentThread().getName() + " 正在做事....");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " 事情做完....");
                countDownLatch.countDown();
            },i+"").start();
        }

        countDownLatch.await();

        System.out.println("子线程任务执行完毕，main线程继续执行....");
    }
}
