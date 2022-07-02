package com.awakeee.hodgepodge.thread1.cyclicBarrier;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 计数器++ 加加
 */
public class CyclicBarrierDemo {


    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        /**
         * 人到齐了开始开会
         */

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10,new Thread(()->{
            System.out.println("全部人都到齐了，可以开始开会了。。。");
        }));


        for(int i=0;i<10;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 还在路上");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 到达会场");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            },i+"").start();
        }



    }


}
