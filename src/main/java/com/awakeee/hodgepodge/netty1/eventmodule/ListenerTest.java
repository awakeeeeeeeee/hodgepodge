package com.awakeee.hodgepodge.netty1.eventmodule;

import java.util.ArrayList;
import java.util.List;

public class ListenerTest {


    public static void main(String[] args) {


        Service service = new Service();
        service.addListener(new Listener() {//异步通知
            @Override
            public void onComplete() {
                System.out.println("任务执行完成");
            }
        });
        service.executeTask();
        System.out.println(11111);

        String result = (String) service.get();//同步阻塞
        System.out.println(result);
        System.out.println(22222);



    }


    static class Service{

        private List<Listener> listenerList = new ArrayList<>();
        private volatile Object result;

        void executeTask(){

             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     try {
                         Thread.sleep(5000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     for(Listener listener : listenerList){
                         listener.onComplete();
                     }
                     result = "get result";
                 }
             }).start();

        }

        void addListener(Listener listener){
            listenerList.add(listener);
        }

        Object get(){
            while (result == null){
               //自旋
            }
            return result;
        }
    }

    interface  Listener{

        void onComplete();
    }

    class MyListener implements Listener{

        @Override
        public void onComplete() {
            System.out.println("任务执行完成了");
        }
    }

}
