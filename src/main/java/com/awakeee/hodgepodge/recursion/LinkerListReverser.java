package com.awakeee.hodgepodge.recursion;

import java.util.Arrays;

public class LinkerListReverser {
    //链表反转

    //1->2->3->4->5
    //1<-2<-3<-4<-5
    public static Node reverse(Node head){

//        if(head == null){
//            return null;
//        }
//
//        if(head.getNext() == null){
//            return head;
//        }
       if(null == head || null == head.getNext()){
            return head;
       }

       Node subNode = reverse(head.getNext());//假设2-5反转成功

       head.getNext().setNext(head);//将2的指针指向1
       head.setNext(null);//将1的指针指向null

       return subNode;
    }

    public static Node reversewithfor(Node head){
        Node newhead = null;//已经反转完成的链表，初始状态指向null
        Node currenthead = head;//还未完成反转的链表 初始状态指向head

        while(currenthead != null){
            Node next = currenthead.getNext();
            currenthead.setNext(newhead);//将当前要完成的节点 反转过来指向已经完成反转的节点
            newhead = currenthead;
            currenthead = next;
        }
        return newhead;
    }

    public static void main(String[] args) {

        Node node = LinkedListCreator.create(Arrays.asList(1,2,3,4,5));
//        PrintlnUtil.printNode(node);
//        Node reverseNode = reverse(node);
        PrintlnUtil.printNode(node);
//        PrintlnUtil.printNode(reverseNode);
//        PrintlnUtil.size(reverseNode);
//        Node reverseNode1 = reversewithfor(node);
//        PrintlnUtil.printNode(reverseNode1);

    }
}
