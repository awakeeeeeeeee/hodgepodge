package com.awakeee.hodgepodge.thread1.pool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SimpleThreadPool {

    //执行线程数
    private volatile int corenum;

    //等待队列
    private LinkedList<Runnable> queue = new LinkedList<>();

    //执行线程计数器
    private volatile int workernum = 0;


    public SimpleThreadPool() {
        corenum = 2;
    }

    public SimpleThreadPool(int corenum) {
        this.corenum = corenum;
    }

    //工作线程
    class Worker extends Thread {

        private Runnable r;

        private ThreadStatus threadStatus;

        public ThreadStatus getThreadStatus() {
            return threadStatus;
        }

        public void setThreadStatus(ThreadStatus threadStatus) {
            this.threadStatus = threadStatus;
        }

        public Worker(Runnable r) {
            this.r = r;
            threadStatus = ThreadStatus.FEE;
        }

        /**
         * 存在的问题：
         * 1.当某个线程执行获取到queue的锁并且此时workernum==corenum的时候，这个线程就会一直把队列中剩余的任务全部执行完才会释放锁
         * 导致其他线程没机会执行任务
         * 2.线程的创建应该和任务隔离开了，不能在任务提交的时候再进行创建，比较好的方法应该是在线程池初始化的时候进行创建，并且让各个线程处于等待，然后提交任务的时候去唤醒全部等待的
         * 线程，进行任务的执行
         *
         */
        @Override
        public void run() {
//            System.out.println("name: "+Thread.currentThread().getName());
            while (this.getThreadStatus() != ThreadStatus.DEAD) {
                synchronized (queue) {
//                    System.out.println(workernum == corenum);
                    while (workernum == corenum) {
                        System.out.println("queue size: "+queue.size());
                        while (queue.isEmpty()) {
                            try {
                                this.setThreadStatus(ThreadStatus.BLOCKED);
                                System.out.println(Thread.currentThread().getName() + "进入等待....");
                                queue.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        Runnable r = queue.removeFirst();
                        r.run();
                        this.setThreadStatus(ThreadStatus.WORKING);
//                        queue.notifyAll();
                    }
//                    r.run();执行的方法必须方在同步块外，否则就变成同步执行了
//                    this.setThreadStatus(ThreadStatus.WORKING);
                }
                r.run();
                this.setThreadStatus(ThreadStatus.WORKING);
            }
        }
    }

    //线程状态
    enum ThreadStatus {
        FEE, WORKING, BLOCKED, DEAD
    }


    public void submit(Runnable r) {
        synchronized (this) {
            if (workernum < corenum) {
                Worker worker = new Worker(r);
                worker.start();
                workernum++;
            } else {
                try {
                    Thread.sleep(3000);//休眠几秒，是想看到让部分线程进入等待的效果，但是这里会造成全部线程都处于等待，因为启动线程
                    //达到10个，并且这10个都把自己的任务执行完后，发现队列还是空的，那么就全部进入wait了
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.addLast(r);
            }
        }
//        System.out.println("workernum: "+workernum);
    }
}
