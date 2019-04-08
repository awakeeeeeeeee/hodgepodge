package com.awakeee.hodgepodge.dataStructure.customerArray;

import java.util.Date;
import java.util.Stack;

public class Array {

    public static void main(String[] args) {


       Date date = new Date();

        //System.out.println(aFunc(50));

        Date date1 = new Date();
        long time = date1.getTime() - date.getTime();
        System.out.println(time);
        System.out.println(time/(1000*60*60) + "小时 " + time/(1000*60) + "分钟 "+ (double)time/1000 + "秒 " + time + "毫秒");

        Stack<Integer> integers = new Stack<>();


        for(Integer integer : integers){
            System.out.println(integer);
        }

    }

    static long aFunc(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return aFunc(n - 1) + aFunc(n - 2);
        }
    }
}
