package com.awakeee.hodgepodge.thread;

import java.util.List;

public abstract class ThreadPool {

    private int size;

    private List<Thread> list;

    private static int index;

    public ThreadPool(int size){
        this.size = size;
        for(int i=0;i<size;i++){
            list.add(new Thread());
        }
    }

    private Thread getThread() throws Exception {
        if(index>list.size()-1){
            throw new Exception("there is no thread");
        }
        index++;
        return list.get(index);
    }

    abstract void execute();



    public static void main(String[] args) throws Exception {

        ThreadPool pool = new ThreadPool(3) {
            @Override
            void execute() {

            }
        };
        Thread t = pool.getThread();
        t.start();
    }
}
