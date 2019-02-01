package com.awakeee.hodgepodge.thread.communication;

import org.junit.Test;

//准备处理数据的线程B正在等待数据变为可用。换句话说，它在等待线程A的一个信号，
// 这个信号使hasDataToProcess()返回true。线程B运行在一个循环里，以等待这个信号：
public class BusyWait {


    @Test
    public void test() throws InterruptedException {

        ShareData shareData = new ShareData();
        Process process = new Process(shareData);
        Process1 process1 = new Process1(shareData);
        Process1 process2 = new Process1(shareData);
        Thread t = new Thread(process);
        Thread t1 = new Thread(process1);
        Thread t2 = new Thread(process2);

        t1.start();
        t2.start();

        //休眠1秒 看process1是否在等待flag被修改

        t.start();

//        while(Thread.activeCount() > 1){
//            Thread.yield();
//        }

        //join之后 main线程会等待t,t1,t2三个线程执行完毕后再继续执行

        t1.join();
        t2.join();
        t.join();

        System.out.println(Thread.currentThread().getName());
        //Thread.currentThread().join();
    }



    class Process implements Runnable{

        private ShareData shareData;

        public Process(ShareData shareData){
            this.shareData = shareData;
        }

        @Override
        public void run() {
                shareData.setFlag(true);
                System.out.println("flag被线程" + Thread.currentThread().getName() + "修改为true");
        }
    }



    class Process1 implements Runnable{

        private ShareData shareData;

        public Process1(ShareData shareData){
            this.shareData = shareData;
        }

        @Override
        public void run() {
            //synchronized (shareData) {//这里如果加上synchronized 那么线程会无限的循环执行，因为其他的线程获取不到锁，
            // 但是synchronized也不会自己释放锁，这个是synchronized的缺点
                while (!shareData.isFlag()) {
                    System.out.println("线程" + Thread.currentThread().getName() + "等待flag被修改");
                }
                System.out.println(Thread.currentThread().getName()+"flag已经被修改,现在可以开始工作了");
            }
        //}
    }
}
