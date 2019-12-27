package com.awakeee.hodgepodge.thread1.pool;

import com.awakeee.hodgepodge.thread.threadpool.ThreadPool_jdk;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {

       SimpleThreadPool simpleThreadPool = new SimpleThreadPool(10);

        for(int i=0;i<40;i++){
            MyTask myTask = new MyTask(i);
            simpleThreadPool.submit(myTask);
        }

    }



    static class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 正在执行task "+taskNum);
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 执行task "+taskNum + "完毕");
        }
    }


}
