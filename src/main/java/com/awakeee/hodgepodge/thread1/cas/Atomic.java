package com.awakeee.hodgepodge.thread1.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(10);
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.getAndAdd(10));
        System.out.println(atomicInteger.get());


        AtomicInteger a = new AtomicInteger(10);
        boolean result = a.compareAndSet(12,20);
        System.out.println(result);

        boolean result1 = a.compareAndSet(10,20);
        System.out.println(result1);

    }
}
