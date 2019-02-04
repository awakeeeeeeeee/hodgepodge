package com.awakeee.hodgepodge.thread.lock;

//饥饿和公平
//饥饿  某些线程可能因为得不到cpu的运行时间一直处于等待状态

//产生饥饿的原因:
// 高优先级线程吞噬所有的低优先级线程的CPU时间。
//线程被永久堵塞在一个等待进入同步块的状态。
//线程在等待一个本身也处于永久等待完成的对象(比如调用这个对象的wait方法)。

import org.junit.Test;

//在Java中实现公平性
public class FairLock {

    Lock lock = new Lock();

    @Test
    public void testLock() throws InterruptedException {
        Proces p = new Proces();

        Thread t = new Thread(p);
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(p);

        t.start();
        t1.start();
        t2.start();

        t.join();
        t1.join();
        t2.join();

    }


    class Lock{
        private boolean isLock = false;
        Thread lockThread;


        private synchronized void lock(){
            while(isLock){
                try {
                    System.out.println("this:"+this);
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isLock = true;
            lockThread = Thread.currentThread();
        }

        private synchronized void unlock(){
            if(lockThread != Thread.currentThread()){
                throw new IllegalMonitorStateException("current threalod not locked this lcok");
            }
            isLock = false;
            lockThread = null;
            notify();
        }
    }

    class Proces implements Runnable{
        @Override
        public void run() {
            lock.lock();
            for(int i=0;i<3;i++){
                System.out.println("i:"+i +" "+Thread.currentThread().getName()+" is working");
            }
            lock.unlock();
        }
    }

}

