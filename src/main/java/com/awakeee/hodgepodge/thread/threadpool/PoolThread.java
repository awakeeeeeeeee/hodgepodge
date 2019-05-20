package com.awakeee.hodgepodge.thread.threadpool;


public class PoolThread extends Thread {

    private BlockingQueue taskQueue = null;
    private boolean       isStopped = false;

    public PoolThread(BlockingQueue queue) {
        taskQueue = queue;
    }

    public void run() {
        while (!isStopped()) {
            try {
                Runnable runnable = (Runnable) taskQueue.take();
                runnable.run();
            } catch(Exception e) {
                // 写日志或者报告异常,
                // 但保持线程池运行.
            }
        }
    }

    public synchronized void toStop() {
        isStopped = true;
        this.interrupt(); // 打断池中线程的 take() 调用.
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}