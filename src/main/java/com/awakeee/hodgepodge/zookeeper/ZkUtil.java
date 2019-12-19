package com.awakeee.hodgepodge.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class ZkUtil {


    public static void main(String[] args) throws KeeperException, InterruptedException {

        ZooKeeper zk = ZkConnection.getZK();

        //创建临时节点
//        zk.create("/testnode","test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        //创建永久节点
//        zk.create("/testnode1","test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        String ctx = "{'create':'success'}";

        //异步创建节点
//        zk.create("/testnode2222", "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new AsyncCallback.StringCallback() {
//            @Override
//            public void processResult(int i, String s, Object o, String s1) {
//                System.out.println("i:"+i);
//                System.out.println("s:"+s);
//                System.out.println("o:"+o);
//                System.out.println("s1:"+s1);
//            }
//        },ctx);
//
        Thread.sleep(2000);

        //修改节点
//        Stat stat = zk.setData("/testnode1","aaa".getBytes(),0);
//        System.out.println(stat.getCversion());

        //删除节点
//        zk.delete("/testnode1",1);

        //查询节点
        List<String> nodes = zk.getChildren("/",false);
        System.out.println(nodes.size());
        for(String s : nodes){
            System.out.println(s);
        }

        //检查节点是否存在
        Stat stat = zk.exists("/aaa",false);
        System.out.println(stat);

    }
}
