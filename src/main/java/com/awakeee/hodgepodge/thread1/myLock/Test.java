package com.awakeee.hodgepodge.thread1.myLock;

public class Test {

    private static int i = 0;

    public static void main(String[] args) {

        Lock lock =  new MyLock();
        Process p = new Process(i, lock);

        for(int i=0;i<10;i++){
            Thread t = new Thread(p);
            t.start();
        }

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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("size: "+lock.blockedSize());
            System.out.println(Thread.currentThread().getName() +" 打印 " + i++);
            lock.unlock();
        }
    }
}
