package com.awakeee.hodgepodge.thread1.producerAndconsumer;

public class WhileTest {

    private static int i = 0;

    public static void main(String[] args) {

        MyThread myThread = new MyThread();

        Thread t = new Thread(myThread);
        Thread t1 = new Thread(myThread);

        t.start();
        t1.start();

    }


    static class MyThread implements Runnable{

        @Override
        public void run() {

            while (true){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() +" 尝试获取锁");
                synchronized (WhileTest.class){
                    System.out.println(Thread.currentThread().getName() + " 获取到了锁");
                    if(i != 0){
                        break;
                    }
                }
            }
        }
    }
}
