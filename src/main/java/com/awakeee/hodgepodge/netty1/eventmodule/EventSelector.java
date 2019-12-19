package com.awakeee.hodgepodge.netty1.eventmodule;

import java.util.LinkedList;

public class EventSelector {


    LinkedList<EventChannel> select(){

//        Queue<EventChannel> queue = new LinkedList<>();
//        queue.add()
        return null;
    }


    public static void main(String[] args) {

        String ss = "https://pcmuat.trplus.com.tw/300x300/sys-master/productImages/h1c/h0c/8938854318113/000000000016093352-gallery-01-20190822100125947.jpg";
        String url = getPcmUrlByHash(ss);
        System.out.println(url);


    }


    public static String getPcmUrlByHash(String url){
        if(url.indexOf("pcm") > 0){
            String head = url.substring(0,url.indexOf(".")) + Math.abs(url.hashCode() % 4 + 1) ;
            System.out.println(head);
            return head + url.substring(url.indexOf("."),url.length());
        }
        return null;
    }

}
