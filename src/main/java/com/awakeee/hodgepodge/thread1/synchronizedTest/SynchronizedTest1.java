package com.awakeee.hodgepodge.thread1.synchronizedTest;

public class SynchronizedTest1 {

    private static int i = 0;

    private static Object object = new Object();

    public static void main(String[] args) {

        for(int i=0;i<10;i++){
            Task task = new Task();
            test(task);
        }

    }


    static class Task implements Runnable{


        public Task() {
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " run ...." + (i++));
        }
    }

    /**
     * synchronized中新的线程执行肯定不是同步的，同步快指的是执行到同步块中的线程同步，不要混淆了
     *
     */
    static void test(Runnable r) {
        synchronized (object) {
            MyThread myThread = new MyThread(r);
            myThread.start();
        }
    }


    static class MyThread extends Thread {

        Runnable r;

        public MyThread(Runnable r) {
            this.r = r;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r.run();
        }
    }
}
