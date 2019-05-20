package com.awakeee.hodgepodge.thread.threadpool;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

    private BlockingQueue taskQueue = null;
    private List<PoolThread> threads = new ArrayList<PoolThread>();
    private boolean isStopped = false;

    protected ThreadPool(int noOfThreads, int maxNoOfTasks) {
        taskQueue = new BlockingQueue();
        taskQueue.setLimit(maxNoOfTasks);

        for (int i=0; i<noOfThreads; i++) {
            threads.add(new PoolThread(taskQueue));
        }
        for (PoolThread thread : threads) {
            thread.start();//从阻塞队列中取出需要执行的任务,并且执行该任务
        }
    }

    public ThreadPool() {
    }

    public synchronized void  execute(Runnable task) {
        if(this.isStopped) throw
                new IllegalStateException("ThreadPool is stopped");

        try {
            this.taskQueue.put(task);//添加任务到阻塞队列中
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void stop() {
        this.isStopped = true;
        for (PoolThread thread : threads) {
            thread.stop();
        }
    }

    @Test
    public void test(){

        ThreadPool threadPool = new ThreadPool(3,10);
        //初始化了三个工作线程不停的尝试去从阻塞队列中取出任务来执行
        //刚开始阻塞队列中是没有任务的，直到下面的线程池开始执行往队列中添加任务，这时候工作线程才能从阻塞队列中获取到需要执行的任务并执行
        for(int i=1;i<=15;i++){
            Task task = new Task(i);
            threadPool.execute(task);
        }

        threadPool.stop();

//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    class Task implements Runnable{

        private int tasknum;

        public Task(int tasknum) {
            this.tasknum = tasknum;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在执行任务:"+tasknum);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程"+Thread.currentThread().getName()+"执行任务"+tasknum+"完毕");
        }
    }

}