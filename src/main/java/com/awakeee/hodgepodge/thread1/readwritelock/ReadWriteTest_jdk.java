package com.awakeee.hodgepodge.thread1.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteTest_jdk {


    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void main(String[] args) {


        WriteProcess writeProcess = new WriteProcess();
        ReadProcess readProcess = new ReadProcess();

        for(int i=0;i<3;i++){
            Thread t = new Thread(readProcess);
            t.start();
        }

        for(int i=0;i<3;i++){
            Thread t = new Thread(writeProcess);
            t.start();
        }


    }


    static class WriteProcess  implements Runnable{

        @Override
        public void run() {
            try{
                writeLock.lock();

                System.out.println("写线程" +Thread.currentThread().getName() + "开始写数据");
                Thread.sleep(500);
                System.out.println("写线程" +Thread.currentThread().getName() + "写数据完成");

            }catch (Exception e){

            }finally {
                writeLock.unlock();
            }
        }
    }


    static class ReadProcess implements Runnable{

        @Override
        public void run() {
            try{
                readLock.lock();
                System.out.println("读线程" +Thread.currentThread().getName() + "开始读数据");
                Thread.sleep(500);
                System.out.println("读线程" +Thread.currentThread().getName() + "读数据完成");
            }catch (Exception e){

            }finally {
                readLock.unlock();
            }
        }
    }
}
