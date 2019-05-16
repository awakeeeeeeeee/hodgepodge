package com.awakeee.hodgepodge.thread.semaphore;

import org.junit.Test;

//信号量 是一个线程同步结构，用于在线程间传递信号，以避免线程中出现信号丢失
//等待线程一直在等待信号，发送信号的线程发送后，等待线程会接受到信号，收到信号后，发送线程会再次发送
public class Semaphore {

    private boolean signal = false;

    public synchronized void take() {
        this.signal = true;
        this.notify();//唤醒某一个等待线程，而不是当前线程。再说当前线程也不需要被唤醒
        System.out.println("线程:" + Thread.currentThread().getName() + "发出信号.....");
    }


    public synchronized void release() throws InterruptedException {
        while (!signal) {
            System.out.println("线程:" + Thread.currentThread().getName() + "正在等待信号.....");
            this.wait();
        }
        System.out.println("线程:" + Thread.currentThread().getName() + "收到信号.....");
        this.signal = false;
    }

    @Test
    public void test() throws InterruptedException {

        Semaphore semaphore = new Semaphore();

        SendingThread sender = new SendingThread(semaphore);

        RecevingThread receiver = new RecevingThread(semaphore);

        receiver.start();

        sender.start();

        receiver.join();
        sender.join();

    }


    public class SendingThread extends Thread {
        Semaphore semaphore = null;

        public SendingThread(Semaphore semaphore) {
            this.semaphore = semaphore;

        }

        public void run() {

            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.semaphore.take();
            }
        }
    }

    public class RecevingThread extends Thread {
        Semaphore semaphore = null;

        public RecevingThread(Semaphore semaphore) {
            this.semaphore = semaphore;

        }

        public void run() {
            while (true) {
                try {
                    this.semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
