package com.awakeee.hodgepodge.thread1.myLock;

public class Test2 {

    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {

        Lock lock =  new MyLock2();
        Process p = new Process(i, lock);

        for(int i=0;i<3;i++){
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
            try {
                lock.lock(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (MyLock2.TimeOutException e) {
                System.out.println(Thread.currentThread().getName() + " out time");
                return;
            }
            working();
            lock.unlock();
        }
    }

    private static void working(){

        System.out.println(Thread.currentThread().getName() + " is working....");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
