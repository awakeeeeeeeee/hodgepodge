package com.awakeee.hodgepodge.thread1.cas;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) {

        System.out.println("===========ABA问题的产生=============");

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean result = atomicReference.compareAndSet(100, 101);
                System.out.println(result);

                boolean result1 = atomicReference.compareAndSet(101, 100);
                System.out.println(result1);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                    boolean result = atomicReference.compareAndSet(100, 2020);
                    System.out.println(result);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=============ABA问题的解决=============");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);//这里的休眠是为了让下面的线程拿到第一次的stamp
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean result = atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                System.out.println(result);

                boolean result1 = atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
                System.out.println(result1);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedReference.getStamp();
                try {
                    Thread.sleep(1000);//这里的休眠是为了让上面的线程先执行完，此时stamp已经被更新，和第一次拿到的stamp的值不同，所以下面的比较交换才会失败，从而解决了ABA问题
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean result = atomicStampedReference.compareAndSet(100, 2020, stamp, stamp + 1);
                System.out.println(result);
            }
        }).start();
    }
}
