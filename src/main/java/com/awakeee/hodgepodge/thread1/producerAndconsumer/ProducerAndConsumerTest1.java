package com.awakeee.hodgepodge.thread1.producerAndconsumer;

public class ProducerAndConsumerTest1 {


    //多生产者多消费者模型 wait部分的逻辑未处理好，正常来说应该是抢到锁的进行生产，如果消费者还未消费，则生产线程需要wait，而不是自旋

    private static volatile int i = 0;

    private static final Object object = new Object();

    private static boolean isProduced = false;

    public static void main(String[] args) throws InterruptedException {

        Producer p = new Producer();
        Consumer c = new Consumer();

        for(int i=0;i<5;i++){
            Thread t = new Thread(p);
            t.start();
        }

        for(int i=0;i<5;i++){
            Thread t = new Thread(c);
            t.start();
        }

    }


    static class Producer implements Runnable{

        @Override
        public void run() {

            while (true){
                System.out.println(Thread.currentThread().getName() + " 正在尝试获取锁");
                synchronized (object){
                    System.out.println(Thread.currentThread().getName() + " 成功获取锁");
                    if(!isProduced){
                        if(i>= 10){
                            break;
                        }
                        System.out.println(Thread.currentThread().getName() + "生产了 "+ (++i));
                        isProduced = true;
                        try {
                            object.wait();//生产完成后，等待消费者消费
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            System.out.println(Thread.currentThread().getName() + " 被唤醒继续生产..");
        }
    }

    static class Consumer implements Runnable{

        @Override
        public void run() {
            while (true){
                synchronized (object){
                    if(isProduced){
                        System.out.println(Thread.currentThread().getName() + "消费了 "+i);
                        isProduced = false;
                        object.notifyAll();//消费完成后，通知生产者生产
                    }
                }
            }
        }
    }
}
