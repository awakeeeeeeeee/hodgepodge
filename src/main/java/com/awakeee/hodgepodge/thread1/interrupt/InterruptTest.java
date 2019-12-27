package com.awakeee.hodgepodge.thread1.interrupt;

public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {

//        interrupt（）是给线程设置中断标志；interrupted（）是检测中断并清除中断状态；
//        isInterrupted（）只检测中断。
// 还有重要的一点就是interrupted（）作用于当前线程，interrupt（）和isInterrupted（）作用于此线程，即代码中调用此方法的实例所代表的线程。



        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start run....");

                while (true){

                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("我收到了中断信号，但是我仍然继续运行");
                        System.out.println(Thread.currentThread().interrupted());
                        System.out.println(Thread.currentThread().interrupted());
                        return;
                    }else{
                        System.out.println("我没有收到中断信号");
                    }
                }

//                System.out.println(Thread.currentThread().getName() + " is interrupt....");
            }
        });

        t.start();
        Thread.sleep(2000);
        t.interrupt();
        System.out.println(t.isInterrupted());
        System.out.println(t.interrupted());


//        System.out.println(Thread.currentThread().getName());
    }
}
