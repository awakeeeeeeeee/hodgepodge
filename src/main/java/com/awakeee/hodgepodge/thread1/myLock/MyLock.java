package com.awakeee.hodgepodge.thread1.myLock;

import java.util.LinkedList;
import java.util.Queue;

//错误的锁 无法保证同步
//逻辑存在问题，原因在于if的判断,假设线程1执行完将lock修改为true,后续的线程全部处于等待，但是一旦线程1执行完后，唤醒全部等待线程
//这个时候，被唤醒的线程都将执行isLock=true的代码，那么执行完这个同步块后，有可能多个线程同时继续执行下面的内容,无法保证后续的逻辑
//是一个原子操作
//正确的做法应该是讲if改为while
public class MyLock implements Lock{

    private boolean isLock = false;

    private Queue<Thread> blockingThread = new LinkedList<>();

    @Override
    public void lock() {

        //第一个线程进来抢到锁，第二个线程进来锁还未释放，则进入等待，并加入等待队列

        synchronized(this){
            if(isLock){
               blockingThread.add(Thread.currentThread());
                System.out.println(Thread.currentThread().getName() + " 进入等待队列");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " 被唤醒");
            isLock = true;

        }
    }

    @Override
    public void unlock() {

        synchronized (this){

            if(isLock){
                isLock = false;
                blockingThread.remove(Thread.currentThread());
                this.notifyAll();
            }
        }
    }

    @Override
    public void lock(long time) {

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
