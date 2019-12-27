package com.awakeee.hodgepodge.thread1.pool;

public class Test1 {

    public static void main(String[] args) {

       SimpleThreadPool_1 simpleThreadPool = new SimpleThreadPool_1(10);

        for(int i=0;i<40;i++){
            MyTask myTask = new MyTask(i);
            simpleThreadPool.submit(myTask);
        }

    }



    static class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 正在执行task "+taskNum);
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 执行task "+taskNum + "完毕");
        }
    }


}
