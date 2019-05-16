package com.awakeee.hodgepodge.thread.communication;

import org.junit.Test;

public class WaitAndNotify {


    @Test
    public void test() throws InterruptedException {

        ShareData shareData = new ShareData();
        shareData.setFlag(true);

        //如果定义不同的共享资源，那么等待线程永远不会被唤醒，同样的道理，如果使用字符串常量作为共享的资源，jvm在处理的时候，会把常量字符串
        //转换成同一个对象，那么会造成错误的唤醒(http://ifeve.com/thread-signaling/ 第七点)
//        ShareData shareData1 = new ShareData();
//        shareData1.setFlag(true);
//        Process process = new Process(shareData1);

        Process process = new Process(shareData);
        Process1 process1 = new Process1(shareData);
        Thread t = new Thread(process);
        Thread t1 = new Thread(process);
        Thread t2 = new Thread(process1);

        t.start();
        t1.start();
        t2.start();

        t.join();
        t1.join();
        t2.join();
    }

    class Process implements Runnable{

        private ShareData shareData;

        public Process(ShareData shareData){
            this.shareData = shareData;
        }

        @Override
        public void run() {//等待线程先启动，然后执行到wait()线程进入非运行状态等待，当process1唤醒后，该线程继续往下执行
            try {
                synchronized (shareData){
                    while(shareData.isFlag()){
                        System.out.println("线程"+Thread.currentThread().getName()+"开始等待");
                        shareData.wait();
                    }
                    System.out.println("线程"+Thread.currentThread().getName()+"等待结束");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    class Process1 implements Runnable{

        private ShareData shareData;

        public Process1(ShareData shareData){
            this.shareData = shareData;
        }

        @Override
        public void run() {

            try{
                Thread.sleep(3000);//给等待线程等待时间
            }catch (Exception e){
                e.printStackTrace();
            }

            synchronized (shareData){
                System.out.println("线程"+Thread.currentThread().getName()+"准备唤醒");
                shareData.setFlag(false);
                //shareData.notify();
                shareData.notifyAll();//当存在多个等待线程等待的时候，必需唤醒全部，否则会有等待线程一直处于等待状态
                System.out.println("线程"+Thread.currentThread().getName()+"唤醒结束");
            }
        }
    }
}
