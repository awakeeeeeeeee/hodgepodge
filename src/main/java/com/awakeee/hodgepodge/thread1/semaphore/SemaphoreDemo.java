package com.awakeee.hodgepodge.thread1.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 多线程多资源，信号量反复使用
 * 线程池类似 多个线程抢占线程池中的资源
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        /**
         * 停车场抢停车位
         * 6辆车抢3个停车位
         */

        Semaphore semaphore = new Semaphore(3);

        for(int i=0;i<6;i++){
            new Thread(()->{

                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到停车位");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "离开停车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },i+"").start();
        }

    }

}
