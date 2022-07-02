package com.awakeee.hodgepodge.thread1.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CompareAndSetLock {

        /**
         * compareandset 可以对没抢到锁的线程进行处理，抛出异常或其他的处理
         */
        private static AtomicInteger val = new AtomicInteger(0);

        private static Thread currentThread;

        public void tryLock() throws Exception {

            boolean isSuccess = val.compareAndSet(0,1);
            if (!isSuccess){
                throw new Exception(Thread.currentThread().getName() + " get lock failed");
            }

            System.out.println(Thread.currentThread().getName() + " get lock");
            currentThread = Thread.currentThread();
            Thread.sleep(10000);

        }

        public void unLock(){

//            if(val.get() == 0){
//                System.out.println(Thread.currentThread().getName() + "is 0" );
//            }

            if(currentThread == Thread.currentThread()){
                boolean result = val.compareAndSet(1,0);
                if(!result){
                    System.out.println(Thread.currentThread().getName() + "is 0" );
                }
            }
        }
}
