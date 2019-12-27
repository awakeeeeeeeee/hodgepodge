package com.awakeee.hodgepodge.thread1.producerAndconsumer;

public class ProducerAndConsumerTest {

    //生产者消费者，没有实际意义，因为消费者没有根据生产者生产的来进行消费

    private static int i = 0;

    private static final Object object = new Object();

    public static void main(String[] args) throws InterruptedException {


        Thread t = new Thread(new Producer());

        Thread t1 = new Thread(new Consumer());

        t.start();
        t1.start();

//        t.join();
//        t1.join();

        System.out.println(t.isAlive());
        System.out.println(t.getState().name());

        System.out.println(t1.isAlive());
        System.out.println(t1.getState().name());

    }


    static class Producer implements Runnable{

        @Override
        public void run() {
            synchronized (object){
                while (true){
//                    try {
//                        Thread.sleep(5);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    System.out.println("p->"+ (i++));
                }
            }
        }
    }

    static class Consumer implements Runnable{

        @Override
        public void run() {
            synchronized (object){
                while (true){
                    System.out.println("c->"+ i);
                }
            }
        }
    }
}
