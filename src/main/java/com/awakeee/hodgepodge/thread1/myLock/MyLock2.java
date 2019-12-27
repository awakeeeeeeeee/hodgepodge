package com.awakeee.hodgepodge.thread1.myLock;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class MyLock2 implements Lock{

    //实现带有超时机制的锁

    private Queue<Thread> waitingThreads = new LinkedList<>();

    private boolean locked = false;

    private Thread currentThread;

    class TimeOutException extends Exception{
        public TimeOutException(String message) {
            super(message);
        }
    }

    @Override
    public void lock()  {

        synchronized (this){
            while (locked){
                waitingThreads.add(Thread.currentThread());
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentThread = Thread.currentThread();
            locked = true;
            waitingThreads.remove(currentThread);
            System.out.println(Thread.currentThread().getName() + " has the lock monitor...wating thread num is "+ waitingThreads.size());
        }
    }

    @Override
    public void unlock()  {
        synchronized (this){
            if(currentThread == Thread.currentThread()){
                locked = false;
                this.notifyAll();
                System.out.println(Thread.currentThread().getName() + " release the lock monitor...");
            }
        }
    }

    @Override
    public void lock(long time) throws TimeOutException, InterruptedException {
        if(time <= 0){
            lock();
            return;
        }
        long maxwatting = System.currentTimeMillis() + time;
        boolean timeout = false;
        synchronized (this){
            while (locked){
                /**
                 * 第二种解决方案
                 * 将等待时间需要计算进来，如果等待就超时了，就不用再去抢了
                 */
                if(timeout){
                    throw new TimeOutException("it is timeout...");
                }
                waitingThreads.add(Thread.currentThread());
//                this.wait(time);
                this.wait();
                long end = System.currentTimeMillis();
                if(end - maxwatting > 0){
                    timeout = true;
                }
            }
//            /**
//             * 想到的第一种解决方案
//             * 抢到锁的时间和最长等待时间进行比较，如果超时，直接抛出异常
//             * 但是会有一个问题，如果再阻塞队列中的等待时间就已经超时了，下面的逻辑就不正确了
//             */
//            long end = System.currentTimeMillis();
//            if(end - maxwatting > 0){
//                throw new TimeOutException("time out");
//            }
            currentThread = Thread.currentThread();
            locked = true;
            waitingThreads.remove(currentThread);
            System.out.println(Thread.currentThread().getName() + " has the lock monitor...");
        }
    }

    @Override
    public void unlock(long time) {
        synchronized (this){
            if(currentThread == Thread.currentThread()){
                locked = false;
                this.notifyAll();
                System.out.println(Thread.currentThread().getName() + " release the lock monitor...");
            }
        }
    }

    @Override
    public void lock(int time) throws InterruptedException {

    }

    @Override
    public void unlock(int time) throws InterruptedException {

    }

}
