package com.awakeee.hodgepodge.thread1.cas;

public class Test {

    public static void main(String[] args) {

        SynchronizedLock lock = new SynchronizedLock();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                lock.dosome();
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                lock.dosome();
//            }
//        }).start();


        CompareAndSetLock lock1 = new CompareAndSetLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    lock1.tryLock();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock1.unLock();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    lock1.tryLock();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock1.unLock();
                }
            }
        }).start();
    }
}
