package com.awakeee.hodgepodge.thread1.myLock;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class MyLock1 implements Lock{

    //解决了锁的同步问题，但是等待队列的数量并不准确，因为可能线程1执行打印数量的时候，线程二刚好还未执行到被加入队列

    private Queue<Thread> waitingThreads = new LinkedList<>();

    private boolean locked = false;

    private Thread currentThread;

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
    public void lock(long time)  {

    }

    @Override
    public void unlock(long time)  {

    }

    @Override
    public void lock(int time) throws InterruptedException {

    }

    @Override
    public void unlock(int time) throws InterruptedException {

    }

}
