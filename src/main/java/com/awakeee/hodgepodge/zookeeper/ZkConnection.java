package com.awakeee.hodgepodge.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ZkConnection {

    private final static String zkPath = "114.67.71.22:2181";

    private final static int timeOut = 5000;

    public static ZooKeeper getZK(){
        try {
            ZooKeeper zooKeeper = new ZooKeeper(zkPath, timeOut, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("收到通知:"+watchedEvent);
                }
            });
            return zooKeeper;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        ZooKeeper zooKeeper = new ZooKeeper("114.67.71.22:2181", 1000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("收到通知:"+watchedEvent);
            }
        });

        System.out.println("开始连接");
        System.out.println("连接状态:"+zooKeeper.getState());

        Thread.sleep(2000);
        System.out.println("连接状态:"+zooKeeper.getState());

        long sessionId = zooKeeper.getSessionId();
        String ssid = Long.toHexString(sessionId);
        System.out.println("ssid:"+ssid);

        byte[] sessionPwd = zooKeeper.getSessionPasswd();
        System.out.println("sesssionid:"+sessionPwd);
        System.out.println("pwd:"+sessionPwd);

        //会话重连
        ZooKeeper zooKeeper1 = new ZooKeeper(zkPath, timeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent);
            }
        },sessionId,sessionPwd);

        System.out.println(zooKeeper1.getSessionId());
        System.out.println(zooKeeper1.getSessionPasswd());

        zooKeeper1.close();
    }


}
