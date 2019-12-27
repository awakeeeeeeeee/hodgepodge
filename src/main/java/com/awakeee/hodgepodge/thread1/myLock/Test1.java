package com.awakeee.hodgepodge.thread1.myLock;

public class Test1 {

    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {

        Lock lock =  new MyLock1();
        Process p = new Process(i, lock);

        for(int i=0;i<3;i++){
            Thread t = new Thread(p);
            t.start();
        }
        lock.unlock();

    }

    static class Process implements Runnable{

        int i ;

        Lock lock;

        public Process(int i, Lock lock) {
            this.i = i;
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            working();
            lock.unlock();
        }
    }

    private static void working(){
        System.out.println(Thread.currentThread().getName() + " is working....");
    }
}
