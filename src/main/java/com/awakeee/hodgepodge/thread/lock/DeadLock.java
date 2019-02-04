package com.awakeee.hodgepodge.thread.lock;

import org.junit.Test;

public class DeadLock {

    private Object resource1 = "resource1";

    private Object resource2 = "resource2";


    @Test
    public void testdeadlock() throws InterruptedException {
        Process p = new Process();
        Process1 p1 = new Process1();
        Thread t = new Thread(p);
        Thread t1 = new Thread(p1);

        t.start();
        t1.start();

        t.join();
        t1.join();
    }

    //线程1先对资源1进行加锁，然后尝试获取资源2，但是此时资源2已经被线程2加锁，线程1就会一只等待线程2释放锁，同时线程2对资源2加锁后
    //又尝试对线程1进行加锁，这样就造成了死锁 线程1等待线程2释放资源2对锁，线程2又在等待线程1释放资源1对锁


    class Process implements Runnable{

        @Override
        public void run() {
            synchronized (resource1){
                System.out.println(Thread.currentThread().getName()+"locked resource1");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource2){//注意这个加锁对操作必需放在前面加锁对同步块里，否则线程执行完后会自动释放锁，就不会产生死锁了
                    System.out.println(Thread.currentThread().getName()+"locked resource2");
                }
            }
        }
    }

    class Process1 implements Runnable{

        @Override
        public void run() {
            synchronized (resource2){
                System.out.println(Thread.currentThread().getName()+"locked resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource1){//注意这个加锁对操作必需放在前面加锁对同步块里，否则线程执行完后会自动释放锁，就不会产生死锁了
                    System.out.println(Thread.currentThread().getName()+"locked resource1");
                }
            }
        }
    }
}
