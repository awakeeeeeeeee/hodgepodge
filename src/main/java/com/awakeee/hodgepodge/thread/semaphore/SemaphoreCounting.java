package com.awakeee.hodgepodge.thread.semaphore;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//信号量 是一个线程同步结构，用于在线程间传递信号，以避免线程中出现信号丢失
//计数器，保存发送量的线程数
public class SemaphoreCounting {

    private int signal = 0;

    public synchronized void take(){
        signal++;
        this.notify();//唤醒某一个等待线程，而不是当前线程。再说当前线程也不需要被唤醒
        //System.out.println("线程:"+Thread.currentThread().getName()+"收到信号.....");
    }


    public synchronized void release() throws InterruptedException {
        while(signal == 0){
            //System.out.println("线程:"+Thread.currentThread().getName()+"正在等待信号.....");
            this.wait();
        }
        signal--;
    }

    @Test
    public void test() throws InterruptedException {
        SemaphoreCounting semaphore = new SemaphoreCounting();
        List<Thread> threadList = new ArrayList<>();
        for(int i=1;i<=10;i++){
            Thread thread = new Thread(new process(semaphore),"第"+i+"个线程开始发送信号");
            threadList.add(thread);
            thread.start();
        }
        for(Thread thread : threadList){
            thread.join();
        }

        System.out.println("信号量计数器:"+semaphore.signal);

    }

    class process implements Runnable{
        private SemaphoreCounting semaphoreCounting;

        public process(SemaphoreCounting semaphoreCounting) {
            this.semaphoreCounting = semaphoreCounting;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name);
            semaphoreCounting.take();
        }
    }




}
