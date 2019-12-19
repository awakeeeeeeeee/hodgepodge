package com.awakeee.hodgepodge.recursion;

import java.util.Arrays;
import java.util.List;

public class LinkedListCreator {

    //递归创建链表


    /**
     *
     * @param datas data collection
     * @return linkedlist node
     */
    //数学归纳法,假设n-1时成立，证明n成立
    //缩小规模，每次缩小必须为1
    public static Node create(List<Integer> datas){
        if(datas.isEmpty()){
            return null;
        }

        Node head = new Node(datas.get(0));

        Node subListNode = create(datas.subList(1,datas.size()));
        head.setNext(subListNode);

        return head;
    }

    //循环写法
    public static Node createwithfor(List<Integer> datas){

       Node pre = null;//指向已经建立的链表的最后一个节点
       Node head = null;

       for(Integer i : datas){
           Node node = new Node(i);
           if(pre != null){
               pre.setNext(node); //pre连接上下一个节点
           }else {
               head = node;
           }
           pre = node;// pre移动到后一个节点
       }

       return head;
    }


    public static void main(String[] args) {
        Node node = create(Arrays.asList(1,2,3,4,5));
        PrintlnUtil.printNode(node);

        Node node1 = createwithfor(Arrays.asList(1,2,3,4,5));
        PrintlnUtil.printNode(node1);

    }
}
