package com.awakeee.hodgepodge.thread.semaphore;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//阻塞队列
//实现类似于带上线的semaphore实现
public class BlockingQueue {

    private List queue = new LinkedList();
    private int limit = 10;

    protected BlockingQueue(int limit) {
        this.limit = limit;
    }

    public BlockingQueue() {
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public synchronized void put(Object item) throws InterruptedException {
        while(queue.size() == limit){
            wait();
        }
        System.out.println(item + "加入队列");
        queue.add(item);
        notify();
//
//        if(queue.size() == 0){
//            notifyAll();
//        }
//        queue.add(item);
        //http://ifeve.com/blocking-queues/一文中的事例是按照上面注视的那样写的，但是按照我这么写，感觉效果是一样的，具体差异在哪里 还不是很清楚，有一种解释
        //假设代码中去掉两个if判断，同时此时queue.size() == 0
        //考虑以下情况
        //1. 线程a先进入dequeue方法，发现size == 0就会wait
        //2. 然后线程b进入enqueue方法，发现size != limit，就会不停的add直到size == limit时wait
        //
        //这个时候你会发现因为没有
        //if( this.queue.size() == 0 ) {
        //notifyAll();
        //}在size == 0 的时候去通知dequeue的线程a，a已经默默的一直在wait中都饿死了！
        //对于
        //if( this.queue.size() == this.limit ) {
        //notifyAll();
        //}也是同理。

        //上面这种解释 我觉得可以保证全部的线程都有机会去获取执行的机会，但是能不能抢到cpu的调度时间就说不准了，但是我上面notify的写法 可能会导致有些处于wait的线程，永远都不会被唤醒，而被饿死
    }

    public synchronized Object take() throws InterruptedException {
        while(queue.size() == 0){
            wait();
        }
        System.out.println("从队列中取出:"+queue.get(0));
//        queue.remove(0);
//        notify();

        if(queue.size() == limit){
            notifyAll();
        }
        return queue.remove(0);

    }

    @Test
    public void test() throws InterruptedException {

        BlockingQueue blockingQueue = new BlockingQueue(5);
        List<Thread> threads = new ArrayList<>();
        List<Thread> threads1 = new ArrayList<>();
        for(int i=1;i<10;i++){
            Thread thread = new Thread(new PutThread(blockingQueue),"第"+i+"个putting线程");
            threads.add(thread);
            thread.start();
        }
        for(int i=3;i<10;i++){
            Thread thread = new Thread(new TakeThread(blockingQueue),"第"+i+"个taking线程");
            threads1.add(thread);
            thread.start();
        }

        for(Thread thread : threads){
            thread.join();
        }

        for(Thread thread : threads1){
            thread.join();
        }

        System.out.println("最终队列:"+blockingQueue.queue.toString());
    }


     class PutThread implements Runnable{

        private BlockingQueue blockingQueue;

        public PutThread(BlockingQueue blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            try {
                blockingQueue.put(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class TakeThread implements Runnable{

        private BlockingQueue blockingQueue;

        public TakeThread(BlockingQueue blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            try {
                blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
