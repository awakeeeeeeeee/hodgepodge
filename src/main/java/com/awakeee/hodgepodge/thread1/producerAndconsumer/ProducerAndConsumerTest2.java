package com.awakeee.hodgepodge.thread1.producerAndconsumer;

public class ProducerAndConsumerTest2 {


    //多生产者多消费者模型

    private static int i = 0;

    private static final Object object = new Object();

    private static boolean isProduced = false;

    public static void main(String[] args) throws InterruptedException {

        Producer p = new Producer();
        Consumer c = new Consumer();

        for(int i=0;i<2;i++){
            Thread t = new Thread(p);
            t.start();
        }

        for (int i=0;i<2;i++){
            Thread t = new Thread(c);
            t.start();
        }

        System.out.println(Thread.activeCount());

    }


    static class Producer implements Runnable {

        @Override
        public void run() {
            System.out.println("生产线程 "+ Thread.currentThread().getName() + " 尝试获取锁");
            synchronized (object){
                System.out.println("生产线程 "+ Thread.currentThread().getName() + " 获取到锁");
                while (true){
                    if(i >= 10){
                        break;
                    }
                    if(!isProduced){
                        System.out.println("生产线程 "+ Thread.currentThread().getName() + " 生产了资源" + ++i);
                        isProduced = true;
                        object.notifyAll();
//                        object.notify();如果是唤醒某一个线程，可能出现全部线程处于等待的情况，因为不知道唤醒的是生产者还是消费者线程
                    }else{
                        //还未被消费则进入等待
                        try {
                            System.out.println("生产线程 "+ Thread.currentThread().getName() + " 进入等待");
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("生产线程 "+ Thread.currentThread().getName() + " 被唤醒，继续进行生产");
                    }
                }
            }

        }
    }

    static class Consumer implements Runnable{

        @Override
        public void run() {
            synchronized (object){
                System.out.println("消费线程 "+ Thread.currentThread().getName() + " 获取到锁");
                while (true){
                    if(isProduced){
                        System.out.println("消费线程 "+ Thread.currentThread().getName() + " 消费了资源"+i);
                        isProduced = false;
                        object.notifyAll();//唤醒全部在等待中的生产者
//                        object.notify();如果是唤醒某一个线程，可能出现全部线程处于等待的情况，因为不知道唤醒的是生产者还是消费者线程
                    }else{
                        //还未生产则进入等待
                        try {
                            System.out.println("消费线程 "+ Thread.currentThread().getName() + " 进入等待");
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("消费线程 "+ Thread.currentThread().getName() + " 被唤醒，继续进行消费");
                    }
                }
            }
        }
    }
}
