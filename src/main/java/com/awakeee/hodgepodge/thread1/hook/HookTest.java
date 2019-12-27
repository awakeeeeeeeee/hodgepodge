package com.awakeee.hodgepodge.thread1.hook;

public class HookTest {

    //注入钩子 应用断开的时候可以让一些资源进行释放
    //如果是kill -9 强制杀死 就无法捕获到

    public static void main(String[] args) throws InterruptedException {

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("i am down....");
                System.out.println(" release the source socket.io....");
            }
        }));

        Thread.sleep(20000);
    }
}
