package com.awakeee.hodgepodge.thread1.cas;

public class SynchronizedLock {

    /**
     * synchronized 无法对没抢到锁的线程进行处理，只能等待
     */
    public synchronized void dosome(){
        try {
            System.out.println(Thread.currentThread().getName() + " get lock");
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
