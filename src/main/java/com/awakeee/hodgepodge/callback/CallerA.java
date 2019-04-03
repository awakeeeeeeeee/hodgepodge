package com.awakeee.hodgepodge.callback;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CallerA {

    void hello(){
        System.out.println("execute calla");
    }


    static class CallB{

        void hello(CallerA callerA) throws InterruptedException {
            System.out.println("execute callb");
            Thread.sleep(1000);

            callerA.hello();
        }

    }

    public static void main(String[] args) throws InterruptedException {

        CallerA callerA  = new CallerA();
        CallB callB = new CallB();
        callB.hello(callerA);

        Thread t = new Thread(new C());
        t.start();

        

    }

    static class C implements Runnable{

        @Override
        public void run() {
            System.out.println("aaa");
        }
    }
}
