package com.awakeee.hodgepodge.thread1.list;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class TestList {

    //bb方法会存在多线程安全问题，改为aa解决

    private ThreadLocal<List<String>> threadLocal = new ThreadLocal<>();

    private List<String> list = new ArrayList<>();


    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        for(String s : list){
            System.out.println(s);
        }
    }

    @Test
    public void test() throws InterruptedException {

        Thread t = new Thread(new Process());
        Thread t1 =new Thread(new Process1());

        t.start();
        t1.start();

        t.join();
        t1.join();
    }

    private void aa(){
        List<String> list = threadLocal.get();
        if(CollectionUtils.isEmpty(list)){
//            System.out.println(Thread.currentThread().getName() + " list is "+list);
            list = new ArrayList<>();
        }
        for(int i=0;i<10;i++){
            list.add(String.valueOf(i));
        }

        threadLocal.set(list);

        List<String> list1 = threadLocal.get();
        System.out.println(Thread.currentThread().getName() + "list.size() " +list.size());

        for(String s : list1){
            System.out.println(s);
        }

    }

    private void bb(){
        for(int i=0;i<10;i++){
            list.add(String.valueOf(i));
        }

        System.out.println(Thread.currentThread().getName() + "list.size() " +list.size());

        for (String s : list){
            System.out.println(s);
        }
    }


    class Process implements Runnable{

        @Override
        public void run() {
            aa();
//            bb();
        }
    }

    class Process1 implements Runnable{

        @Override
        public void run() {
           aa();
//           bb();
        }
    }

}
