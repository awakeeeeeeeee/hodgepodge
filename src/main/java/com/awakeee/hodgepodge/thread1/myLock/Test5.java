package com.awakeee.hodgepodge.thread1.myLock;


public class Test5 {


    private static TestQueue[] testQueues = new TestQueue[100];

    private static int count = 0;

    private static int head = 0;

    private static int tail = 0;

    public static void main(String[] args) {

       for(int i=0;i<100;i++){
           testQueues[tail] = new TestQueue(i);
           //循环往数组中放入数据，当达到数组的长度，重新从0开始放
           tail = (tail + 1) % testQueues.length;
           System.out.println("tail: "+tail);
           count++;
       }

        for(int i=0;i<100;i++){
            TestQueue testQueue = testQueues[head];
            //循环往数组中拿出数据，当达到数组的长度，重新从0开始拿
            head = (head + 1) % testQueues.length;
            System.out.println("head: "+head);
            count--;
        }




    }


    static class TestQueue{

        public TestQueue(int i) {
            this.i = i;
        }

        private int i;

        public void setI(int i) {
            this.i = i;
        }

        public int getI() {
            return i;
        }
    }
}
